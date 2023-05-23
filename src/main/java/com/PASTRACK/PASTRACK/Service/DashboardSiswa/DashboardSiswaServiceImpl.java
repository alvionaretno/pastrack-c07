package com.PASTRACK.PASTRACK.Service.DashboardSiswa;

import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.DashboardGuruRequest.MatpelAverageScore;
import com.PASTRACK.PASTRACK.DashboardGuruRequest.StudentAverageScoreResponse;
import com.PASTRACK.PASTRACK.DashboardSiswaRequest.*;
import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Repository.KomponenDB;
import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
import com.PASTRACK.PASTRACK.Service.DashboardGuru.DashboardGuruService;
import com.PASTRACK.PASTRACK.Service.Kelas.KelasService;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanResponse;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Repository.StudentDB;
import com.PASTRACK.PASTRACK.Repository.StudentMatpelDB;
import com.PASTRACK.PASTRACK.Service.Peminatan.PeminatanService;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import com.PASTRACK.PASTRACK.Service.StudentMatpel.StudentMatpelService;

@Service
@Transactional
public class DashboardSiswaServiceImpl implements DashboardSiswaService {
    @Autowired
    private MatpelDB matpelDB;

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private KomponenDB komponenDB;

    @Autowired
    private StudentMatpelDB studentMatpelDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMatpelService studentMatpelService;

    @Autowired
    private PeminatanService peminatanService;

    @Autowired
    private DashboardGuruService dashboardGuruService;

    @Autowired
    private AngkatanService angkatanService;

    @Autowired
    private KelasService kelasService;

    @Autowired
    private SemesterService semesterService;

    @Override
    public List<PencapaianNilaiPerMatpel> getNilaiPerMatpel(String username) {
        List<PencapaianNilaiPerMatpel> listPencapaian = new ArrayList<PencapaianNilaiPerMatpel>();
        Optional<StudentModel> x = studentService.getUserById(username);
        StudentModel student = x.get();
        List<PeminatanModel> peminatanInSiswa = peminatanService.listPeminatanModelInSiswa(username);
        // Map<PeminatanModel, List<StudentMataPelajaranModel>> map = new HashMap<>();
        for (PeminatanModel peminatan : peminatanInSiswa) {
            List<StudentMataPelajaranModel> listSM = studentMatpelService.getListStudentMatpelByPeminatan(String.valueOf(peminatan), student);
            // map.put(peminatan, listSM);
            // Sorting sesuai urutan semester
            Map<String, Integer> nilaiPerSemester = new HashMap<>();
            List<SemesterModel> listSemester = new ArrayList<>();
            for (StudentMataPelajaranModel sm : listSM) {
                listSemester.add(sm.getMatapelajaran().getSemester());
            }
            semesterService.sortSemester(listSemester);
            for (StudentMataPelajaranModel studentMatpel : listSM) {
                int index = listSemester.indexOf(studentMatpel.getMatapelajaran().getSemester());
                String labelSemester = "Semester " + index;
                nilaiPerSemester.put(labelSemester, studentMatpel.getNilai_komponen());
            }
            PencapaianNilaiPerMatpel pencapaian = new PencapaianNilaiPerMatpel(nilaiPerSemester, peminatan);
            listPencapaian.add(pencapaian);
        }
        return listPencapaian;
    }

    @Override
    public List<PencapaianNilaiAllMatpel> getNilaiRataRata(String username) {
        Optional<StudentModel> student = studentService.getUserById(username);
        StudentModel siswa = student.get();
        List<SemesterModel> listSemester = semesterService.getListSortedSemesterInStudent(siswa);
        List<PencapaianNilaiAllMatpel> listAllPencapaian = new ArrayList<>();
        int counter = 0;
        for (SemesterModel smstr : listSemester) {
            counter++;
            List<StudentMataPelajaranModel> listSMInSemester = studentMatpelService.getListStudentMatpelBySemester(smstr, username);
            int sumNilai = 0;
            for (StudentMataPelajaranModel sm : listSMInSemester) {
                int valueNilai = sm.getNilai_komponen();
                sumNilai += valueNilai;
            }
            int pembagi = listSMInSemester.size();
            int result = sumNilai/pembagi;
            String namaSemester = "Semester " + counter;
            PencapaianNilaiAllMatpel pencapaian = new PencapaianNilaiAllMatpel(namaSemester, result);
            listAllPencapaian.add(pencapaian);
        }
        return listAllPencapaian;
    }

    @Override
    public void generateAllNilaiMatpel(String usernameSiswa) {
        Optional<StudentModel> x = studentService.getUserById(usernameSiswa);
        StudentModel student = x.get();
        List<StudentMataPelajaranModel> listStudentMatpel = student.getNilaiAkhir();
        for (StudentMataPelajaranModel studentMatpel : listStudentMatpel) {
            studentMatpelService.generateNilaiStudentMatpel(studentMatpel);
            studentMatpelDB.save(studentMatpel);
        }
    }

    // Get All Ranking Siswa
    public allRankingSiswa getAllRankingSiswa(String usernameSiswa){
        int rankingAngkatanAllSemester = getStudentRankingInAngkatan(usernameSiswa);
        int rankingKelasCurrentSemester = getStudentRankingInKelas(usernameSiswa);
        int rankingAngkatanCurrentSemester = getStudentRankingInAngkatanCurrentSemester(usernameSiswa);

        allRankingSiswa allRanking = new allRankingSiswa(rankingAngkatanAllSemester, rankingKelasCurrentSemester,rankingAngkatanCurrentSemester);

        return allRanking;
    }


    //Ranking Siswa di Angkatannya All Semester
    public int getStudentRankingInAngkatan(String username) {
        AngkatanModel angkatanModel = studentService.getUserById(username).get().getAngkatan();
        List<StudentModel> siswaList = angkatanModel.getListStudent();
        List<StudentAverageScoreResponse> result = new ArrayList<>();

        // Populate the result list
        for (StudentModel siswa : siswaList) {
            double averageFinalScoreSiswa = dashboardGuruService.getRataRataNilaiSiswaDirectly(siswa.getUsername());
            result.add(new StudentAverageScoreResponse(siswa, averageFinalScoreSiswa));
        }

        // Sort the result by average score in descending order
        Collections.sort(result, Collections.reverseOrder());

        // Find the student's ranking by username
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getStudent().getUsername().equals(username)) {
                return i + 1; // Return the ranking (1-indexed)
            }
        }

        // If the student is not found, return -1 or throw an exception indicating the student is not found
        return -1;
    }

    //Ranking Siswa di Kelasnya semester ini
    public int getStudentRankingInKelas(String username) {
        KelasModel kelasModel = kelasService.getKelasCurrentSemester(username);
        List<StudentModel> siswaList = kelasModel.getListMurid();
        List<StudentAverageScoreResponse> result = new ArrayList<>();

        // Populate the result list
        for (StudentModel siswa : siswaList) {
            double averageFinalScoreSiswa = dashboardGuruService.getRataRataNilaiSiswaDirectly(siswa.getUsername());
            result.add(new StudentAverageScoreResponse(siswa, averageFinalScoreSiswa));
        }

        // Sort the result by average score in descending order
        Collections.sort(result, Collections.reverseOrder());

        // Find the student's ranking by username
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getStudent().getUsername().equals(username)) {
                return i + 1; // Return the ranking (1-indexed)
            }
        }

        // If the student is not found, return -1 or throw an exception indicating the student is not found
        return -1;
    }

    @Override
    public double getRataRataNilaiSiswaCurrentSemester(String usernameSiswa) {
        // Get the current semester
        SemesterModel currentSemester = semesterService.getCurrentSemester();

        // Get student and their classes
        Optional<StudentModel> student = studentService.getUserById(usernameSiswa);
        StudentModel studentX = student.get();
        List<KelasModel> listKelasSiswaX = studentX.getListKelas();

        // Collect all the subjects the student is enrolled in the current semester
        List<MataPelajaranModel> mataPelajaranSiswa = new ArrayList<>();
        for (KelasModel kelasSiswa : listKelasSiswaX) {
            // Filter the subjects based on the current semester
            List<MataPelajaranModel> listMatpelInClass = kelasSiswa.getListMataPelajaran().stream()
                    .filter(matpel -> matpel.getSemester().equals(currentSemester))
                    .collect(Collectors.toList());

            mataPelajaranSiswa.addAll(listMatpelInClass);
        }

        double nilaiAkhir = 0.0;

        List<MatpelAverageScore> listNilaiAkhirMatpel = new ArrayList<>();
        for (MataPelajaranModel matpels : mataPelajaranSiswa) {
            nilaiAkhir = 0.0; // initialize the nilaiAkhir variable here
            List<getComponent> listKomponen = komponenDB.getAllKomponenSiswa(studentX, matpels);
            for (getComponent komponen : listKomponen) {
                double bobot = komponen.getBobot();
                double nilai = komponen.getNilai();
                double nilaiPembobotan = (bobot * nilai) / 100;

                // Store the result in the variable
                nilaiAkhir += nilaiPembobotan;
            }
            MatpelAverageScore nilaiAkhirMatpel = new MatpelAverageScore(matpels, nilaiAkhir);
            listNilaiAkhirMatpel.add(nilaiAkhirMatpel);
        }

        double sumNilai = 0;
        Integer count = 0;
        for (MatpelAverageScore nilaiAkhirMatpel : listNilaiAkhirMatpel) {
            sumNilai += nilaiAkhirMatpel.getNilaiAkhirMatpel();
            count++;
        }

        double nilaiRataRataAkhir = count > 0 ? sumNilai / count : 0.0;
        return nilaiRataRataAkhir;
    }

    //ranking student di angkatannya pada semester ini
    public int getStudentRankingInAngkatanCurrentSemester(String username) {
        AngkatanModel angkatanModel = studentService.getUserById(username).get().getAngkatan();
        List<StudentModel> siswaList = angkatanModel.getListStudent();
        List<StudentAverageScoreResponse> result = new ArrayList<>();

        // Populate the result list
        for (StudentModel siswa : siswaList) {
            double averageFinalScoreSiswa = getRataRataNilaiSiswaCurrentSemester(username);
            result.add(new StudentAverageScoreResponse(siswa, averageFinalScoreSiswa));
        }

        // Sort the result by average score in descending order
        Collections.sort(result, Collections.reverseOrder());

        // Find the student's ranking by username
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getStudent().getUsername().equals(username)) {
                return i + 1; // Return the ranking (1-indexed)
            }
        }

        // If the student is not found, return -1 or throw an exception indicating the student is not found
        return -1;
    }

    public List<PencapaianNilaiPerMatpel> getNilaiMatpel(String username, String namaPeminatan) {
        Optional<StudentModel> x = studentService.getUserById(username);
        StudentModel student = x.get();
        // PeminatanModel peminatan = peminatanService.getPeminatanByNama(namaPeminatan);
        List<StudentMataPelajaranModel> listSM = studentMatpelService.getListStudentMatpelByPeminatan(namaPeminatan, student);
        List<SemesterModel> listSemester = new ArrayList<>();
        for (StudentMataPelajaranModel sm : listSM) {
            listSemester.add(sm.getMatapelajaran().getSemester());
        }
        semesterService.sortSemester(listSemester);
        List<PencapaianNilaiPerMatpel> listPencapaian = new ArrayList<>();
        for (int i = 0; i < listSM.size(); i++) {
            listPencapaian.add(new PencapaianNilaiPerMatpel());
        }
        for (StudentMataPelajaranModel studentMatpel : listSM) {
            int index = listSemester.indexOf(studentMatpel.getMatapelajaran().getSemester());
            int nomorSemester = index + 1;
            String labelSemester = "Semester " + nomorSemester;
            PencapaianNilaiPerMatpel pencapaian = listPencapaian.get(index);
            pencapaian.setSemester(labelSemester);
            pencapaian.setNilaiAkhir(studentMatpel.getNilai_komponen());
        }
        return listPencapaian;
    }

    // PBI 46-47
    // calculate score in given semester
    @Override
    public double getRataRataNilaiSiswaGivenSemester(String usernameSiswa, SemesterModel semester) {
        // Get the student by username
        Optional<StudentModel> student = studentService.getUserById(usernameSiswa);
        StudentModel studentX = student.get();
        List<KelasModel> listKelasSiswaX = studentX.getListKelas();

        // Loop through each class and calculate the score for the given semester
        List<MataPelajaranModel> mataPelajaranSiswa = new ArrayList<>();
        for (KelasModel kelasSiswa : listKelasSiswaX) {
            if (kelasSiswa.getSemester().equals(semester)) {
                List<MataPelajaranModel> listMatpelInClass = kelasSiswa.getListMataPelajaran();
                mataPelajaranSiswa.addAll(listMatpelInClass);
            }
        }

        double nilaiAkhir = 0.0;
        List<MatpelAverageScore> listNilaiAkhirMatpel = new ArrayList<>();
        for (MataPelajaranModel matpel : mataPelajaranSiswa) {
            nilaiAkhir = 0.0;
            List<getComponent> listKomponen = komponenDB.getAllKomponenSiswa(studentX, matpel);
            for (getComponent komponen : listKomponen) {
                double bobot = komponen.getBobot();
                double nilai = komponen.getNilai();
                double nilaiPembobotan = (bobot * nilai) / 100;
                nilaiAkhir += nilaiPembobotan;
            }
            MatpelAverageScore nilaiAkhirMatpel = new MatpelAverageScore(matpel, nilaiAkhir);
            listNilaiAkhirMatpel.add(nilaiAkhirMatpel);
        }

        double sumNilai = 0;
        Integer count = 0;
        for (MatpelAverageScore nilaiAkhirMatpel : listNilaiAkhirMatpel) {
            sumNilai += nilaiAkhirMatpel.getNilaiAkhirMatpel();
            count++;
        }

        double nilaiRataRataAkhir = sumNilai / count;
        return nilaiRataRataAkhir;
    }
    public List<StudentScoreDTO> getStudentScoresBySemester(String username) {
        // Get the student by username
        Optional<StudentModel> student = studentService.getUserById(username);
        StudentModel studentX = student.get();

        // Get the list of classes for the student
        List<KelasModel> listKelasSiswaX = studentX.getListKelas();

        // Initialize the result list
        List<StudentScoreDTO> scores = new ArrayList<>();

        // Loop through each class and calculate the score for each semester
        for (KelasModel kelasSiswa : listKelasSiswaX) {
            SemesterModel semester = kelasSiswa.getSemester();
            double averageScore = getRataRataNilaiSiswaGivenSemester(studentX.getUsername(), semester);
            StudentScoreDTO scoreDTO = new StudentScoreDTO(semester.getId(), averageScore);
            scores.add(scoreDTO);
        }

        return scores;
    }



}
