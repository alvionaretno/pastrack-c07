package com.PASTRACK.PASTRACK.Service.DashboardGuru;

import java.util.*;
import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.DashboardGuruRequest.*;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Repository.AngkatanDB;
import com.PASTRACK.PASTRACK.Repository.KomponenDB;
import com.PASTRACK.PASTRACK.Repository.NilaiAngkatanDB;
import com.PASTRACK.PASTRACK.Repository.StudentDB;
import com.PASTRACK.PASTRACK.Service.Angkatan.AngkatanService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.NilaiAngkatan.NilaiAngkatanService;
import com.PASTRACK.PASTRACK.Service.StudentMatpel.StudentMatpelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
@Service
@Transactional
public class DashboardGuruServiceImpl implements DashboardGuruService {
    @Autowired
    private NilaiAngkatanDB nilaiAngkatanDB;

    @Autowired
    private AngkatanDB angkatanDB;

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private KomponenDB komponenDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MatpelService matpelService;

    @Autowired
    private StudentMatpelService studentMatpelService;

    @Autowired
    private NilaiAngkatanService nilaiAngakanService;

    @Autowired
    private DashboardGuruService dashboardGuruService;

    @Autowired
    private AngkatanService angkatanService;


    //Get all data so multiple dashboard can use one API only
    @Override
    public DashboardGuruResponse getAllData(DashboardGuruRequest request) {
        List<MatpelAverageScore> pbi4041 = getAverageScoreByMataPelajaranAndTeacher(request.getUsernameGuru());

        //PBI 42-43
        List<AngkatanAverageScore> pbi4243 = getAverageScoreByAngkatan();

        Map<String, Integer> pbi4445 = getScoreRangeFrequency(request.getAngkatanId());

        List<StudentAverageScoreResponse> pbi5051 = getPerankinganSiswa(request.getAngkatanId(),request.getPage(), request.getSize());

        DashboardGuruResponse dashboardGuruResponse = new DashboardGuruResponse(pbi4041, pbi4243,pbi4445,pbi5051);
        return dashboardGuruResponse;
    }

    //PBI 40-41
    @Override
    public List<MatpelAverageScore> getAverageScoreByMataPelajaranAndTeacher(String usernameGuru) {
        List<MatpelAverageScore> matpelAverageScores = new ArrayList<>();
        List<Double> averageScoreStudent = new ArrayList<>();

        // Get all the mata pelajaran taught by the teacher
        List<MataPelajaranModel> mataPelajaranList = matpelService.getListMatpelByGuru(usernameGuru);

        for (MataPelajaranModel mataPelajaran : mataPelajaranList) {
            double sumNilaiMatpel = 0;
            //int count = 0;

            // Loop through all the students taking the mata pelajaran
            List<StudentModel> studentInMataPelajaranList = studentMatpelService.getStudentsByMataPelajaran(mataPelajaran);
            for (StudentModel studentMataPelajaran : studentInMataPelajaranList) {
                // Get the nilai akhir of the student
                averageScoreStudent.clear();

                double nilaiAkhir = 0.0;
                //int numKomponen = mataPelajaran.getListKomponen().size();
                List<getComponent> listKomponen = komponenDB.getAllKomponenSiswa(studentMataPelajaran, mataPelajaran);
                for (getComponent komponen : listKomponen) {
                    double bobot = komponen.getBobot();
                    double nilai = komponen.getNilai();
                    double nilaiPembobotan = (bobot * nilai)/100;
                    nilaiAkhir += nilaiPembobotan;

                }


                averageScoreStudent.add(nilaiAkhir);
            }

            for(Double averageScore: averageScoreStudent){
                sumNilaiMatpel += averageScore;
            }

            // Calculate the average score
            sumNilaiMatpel = sumNilaiMatpel / averageScoreStudent.size();

            // Create a new MatpelAverageScore object and add it to the list
            MatpelAverageScore matpelAverageScore = new MatpelAverageScore(mataPelajaran, sumNilaiMatpel);
            matpelAverageScores.add(matpelAverageScore);
        }

        return matpelAverageScores;
    }


    //PBI 42-43

    public List<AngkatanAverageScore> getAverageScoreByAngkatan() {
        List<AngkatanModel> listAngkatan = angkatanDB.findAll();
        List<AngkatanAverageScore> result = new ArrayList<>();

        double sum = 0.0;
        int count = 0;

        for (AngkatanModel angkatanX : listAngkatan) {
            List<StudentModel> listStudent = angkatanX.getListStudent();

            for (StudentModel student : listStudent) {
                double studentScore = getRataRataNilaiSiswaDirectly(student.getUsername());
                sum += studentScore;
                count++;
            }

            if (count > 0) {
                double averageScore = sum / count;
                AngkatanAverageScore angkatanAverageScore = new AngkatanAverageScore(angkatanX.getId(), angkatanX.getAngkatan(), averageScore);
                result.add(angkatanAverageScore);
            }
        }
        return result;
    }

    @Override
    public NilaiAngkatanModel averageScorePerAngkatan(Long angkatanId) {
        NilaiAngkatanModel nilaiAngkatanModel = new NilaiAngkatanModel();

        double sumNilai = 0.0;
        Integer banyakSiswaAngkatanX = 0;
        List<StudentModel> listSiswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);
        for (StudentModel siswa:listSiswaInAngkatanX) {
            double rataRataNilai = dashboardGuruService.getRataRataNilaiSiswaDirectly(siswa.getUsername());
            sumNilai += rataRataNilai;
            banyakSiswaAngkatanX++;
        }

        double nilaiRataRataAngkatan = sumNilai/banyakSiswaAngkatanX;

        nilaiAngkatanModel.setAngkatan(angkatanService.getAngkatanById(angkatanId));
        nilaiAngkatanModel.setNilaiAngkatan(nilaiRataRataAngkatan);
        return nilaiAngkatanDB.save(nilaiAngkatanModel);
    }

    @Override
    public List<NilaiAngkatanModel> averageScoreAllAngkatan() {
        List<AngkatanModel> allAngkatan = angkatanService.findAll();
        for(AngkatanModel angkatan:allAngkatan){
            dashboardGuruService.averageScorePerAngkatan(angkatan.getId());
        }
        List<NilaiAngkatanModel> nilaiAllAngkatan = nilaiAngkatanDB.findAll();
        return nilaiAllAngkatan;
    }

    @Override
    public List<StudentModel> getSiswaByTahunMasuk(Long angkatanId) {
        List<StudentModel> siswaInAngkatanX = studentService.getStudentByTahunMasuk(angkatanId);
        return siswaInAngkatanX;
    }

    @Override
    public List<NilaiAngkatanModel> getNilaiAkhirPerAngkatan(NilaiAngkatanRequest[] listAngkatan) {
        for (int i = 0; i < listAngkatan.length; i++) {
            AngkatanModel angkatan = angkatanService.getAngkatanById(listAngkatan[i].getAngkatanId());
        }
        return dashboardGuruService.averageScoreAllAngkatan();
    }

    //PBI 44-45
    public Map<String, Integer> getScoreRangeFrequency(Long idAngkatan) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        List<StudentModel> students = studentDB.findByTahunMasuk(idAngkatan);

        // Initialize frequency map with all score ranges and frequency of zero
        initializeFrequencyMap(frequencyMap);

        for (StudentModel student : students) {
            double averageScore = getRataRataNilaiSiswaDirectly(student.getUsername());
            String range = getScoreRange(averageScore);
            frequencyMap.merge(range, 1, Integer::sum);
        }

        return frequencyMap;
    }

    private void initializeFrequencyMap(Map<String, Integer> frequencyMap) {
        // Define all score ranges
        String[] scoreRanges = {"91-100", "81-90", "71-80", "61-70", "41-60", "11-40", "0-10"};

        // Initialize frequency of each score range as zero
        for (String range : scoreRanges) {
            frequencyMap.put(range, 0);
        }
    }

    private String getScoreRange(double score) {
        if (score >= 91 && score <= 100) {
            return "91-100";
        } else if (score >= 81 && score <= 90) {
            return "81-90";
        } else if (score >= 71 && score <= 80) {
            return "71-80";
        } else if (score >= 61 && score <= 70) {
            return "61-70";
        } else if (score >= 41 && score <= 60) {
            return "41-60";
        } else if (score >= 11 && score <= 40) {
            return "11-40";
        } else {
            return "0-10";
        }
    }


    //version 1
    @Override
    public List<Double> rataRataNilaiSiswaAngkatanX(Long angkatanId) {
        List<Double> listRataRataNilaiAllAngkatanX = new ArrayList<>();
        List<String> listNamaNamaAllAngkatanX = new ArrayList<>();
        List<StudentModel> siswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);

        for(StudentModel siswa : siswaInAngkatanX){
            double nilaiAkhirSemester = dashboardGuruService.getRataRataNilaiSiswaDirectly(siswa.getUsername());
            listNamaNamaAllAngkatanX.add(siswa.getNama());
            listRataRataNilaiAllAngkatanX.add(nilaiAkhirSemester);
        }
        return listRataRataNilaiAllAngkatanX;
    }

    //version 2
    @Override
    public NilaiSemesterModel rataRataNilaiAkhirSemesterSiswaAngkatanX(Long angkatanId) {
        NilaiSemesterModel nilaiAkhirSemesterModel = new NilaiSemesterModel();
        List<StudentModel> siswaInAngkatanX = dashboardGuruService.getSiswaByTahunMasuk(angkatanId);

        for(StudentModel siswa : siswaInAngkatanX){
            double nilaiAkhirSemester = dashboardGuruService.getRataRataNilaiSiswaDirectly(siswa.getUsername());
            nilaiAkhirSemesterModel.setNilaiAkhirSemester(nilaiAkhirSemester);
            nilaiAkhirSemesterModel.setAngkatan(angkatanService.getAngkatanById(angkatanId));
            nilaiAkhirSemesterModel.setStudent(siswa);
        }
        return nilaiAkhirSemesterModel;
    }

    //PBI 50-51

    @Override
    public double getRataRataNilaiSiswaDirectly(String usernameSiswa) {

        //get student dan kelas nya
        Optional<StudentModel> student = studentService.getUserById(usernameSiswa);
        StudentModel studentX = student.get();
        List<KelasModel> listKelasSiswaX = studentX.getListKelas();
        //List<StudentMataPelajaranModel> listNilaiAkhir = studentX.getNilaiAkhir();

        //looping setiap kelas yang dimiliki siswa agar kita bisa mendapatkan semua mata pelajaran yang siswa miliki
        List<MataPelajaranModel> mataPelajaranSiswa = new ArrayList<>();
        for (KelasModel kelasSiswa: listKelasSiswaX){
            List<MataPelajaranModel> listMatpelInClass = kelasSiswa.getListMataPelajaran();
            for(MataPelajaranModel matpel: listMatpelInClass){
                mataPelajaranSiswa.add(matpel);
            }
        }

        double nilaiAkhir = 0.0;

        List<MatpelAverageScore> listNilaiAkhirMatpel = new ArrayList<>();
        for(MataPelajaranModel matpels:mataPelajaranSiswa){
            nilaiAkhir = 0.0; // initialize the nilaiAkhir variable here
            List<getComponent> listKomponenSiswa = komponenDB.getAllKomponenSiswa(studentX, matpels);
            for (getComponent komponen : listKomponenSiswa) {
                double bobot = komponen.getBobot();
                double nilai = komponen.getNilai();
                double nilaiPembobotan = (bobot * nilai)/100;

                //disimpen dimana?
                nilaiAkhir += nilaiPembobotan;

            }
            MatpelAverageScore nilaiAkhirMatpel = new MatpelAverageScore(matpels, nilaiAkhir);
            listNilaiAkhirMatpel.add(nilaiAkhirMatpel);
        }


        double sumNilai = 0;
        Integer count = 0;
        for(MatpelAverageScore nilaiAkhirMatpel: listNilaiAkhirMatpel){
            sumNilai += nilaiAkhirMatpel.getNilaiAkhirMatpel();
            count++;
        }

        double nilaiRataRataAkhir = sumNilai/count;
        return nilaiRataRataAkhir;


    }



    // PBI 50-51: Versi Pagination

    @Override
    public List<StudentAverageScoreResponse> getPerankinganSiswa(Long idAngkatan, int page, int size) {
        AngkatanModel angkatanModel = angkatanService.getAngkatanById(idAngkatan);
        List<StudentModel> siswaList = angkatanModel.getListStudent();
        List<StudentAverageScoreResponse> result = new ArrayList<>();
        StudentMataPelajaranModel studentMatpel = new StudentMataPelajaranModel();
        studentMatpelService.generateNilaiStudentMatpel(studentMatpel);
        for (StudentModel siswa : siswaList) {
            double averageFinalScoreSiswa = dashboardGuruService.getRataRataNilaiSiswaDirectly(siswa.getUsername());
            result.add(new StudentAverageScoreResponse(siswa, averageFinalScoreSiswa));
        }

        // Sort the result by average score in descending order
        Collections.sort(result, Collections.reverseOrder());

        // Assign the ranking to each student
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setRanking(i + 1);
        }

        // Perform pagination
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, result.size());
        return result.subList(startIndex, endIndex);
    }




}