package com.PASTRACK.PASTRACK.Service.Kelas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.KelasRequest.*;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Repository.SemesterDB;
import com.PASTRACK.PASTRACK.Repository.StudentKomponenDB;
import com.PASTRACK.PASTRACK.Repository.StudentMatpelDB;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.Komponen.KomponenService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import com.PASTRACK.PASTRACK.Service.StudentKomponen.StudentKomponenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Repository.KelasDB;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;

@Service
@Transactional
public class KelasServiceImpl implements KelasService {

    @Autowired
    private KelasDB kelasDB;

    @Autowired
    private MatpelDB matpelDB;

    @Autowired
    private SemesterDB semesterDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GuruService guruService;

    @Autowired
    private MatpelService matpelService;

    @Autowired
    private KelasService kelasService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private StudentMatpelDB studentMatpelDB;

    @Autowired
    private KomponenService komponenService;

    //Retrieve All Kelas
    @Override
    public List<addKelasResponse> getAllKelas() {
        List<KelasModel> listKelas = kelasDB.findAll();
        List<addKelasResponse> listKelasResponse = new ArrayList<addKelasResponse>();

        for(KelasModel kelas: listKelas){
            addKelasResponse responseKelas = new addKelasResponse(kelas.getId(), kelas.getNamaKelas(), kelas.getSemester().getId(),kelas.getGuru().getUsername(), kelas.getListMurid(),kelas.getListMataPelajaran());
            listKelasResponse.add(responseKelas);
        }
        return listKelasResponse;
    }

    @Override
    public List<SemesterModel> getAllSemester() {
        return semesterService.findAll();
    }

    //Create Kelas
    @Override
    public addKelasResponse createKelas(addKelasRequest kelas) {
        KelasModel kelasModel = new KelasModel();
        System.out.println(kelas);
        kelasModel.setNamaKelas(kelas.getNamaKelas());
        kelasModel.setSemester(semesterService.getSemesterById(kelas.getSemesterId()));
        GuruModel guru = guruService.getGuruByUsername(kelas.getUsernameGuru());
        kelasModel.setGuru(guru);
        guru.getListKelas().add(kelasModel);
        kelasDB.save(kelasModel);

        // response
        Long semesterId = kelasModel.getSemester().getId();
        String usernameGuru = kelasModel.getGuru().getUsername();
        List<StudentModel> listStudent = kelasModel.getListMurid();
        List<MataPelajaranModel> listMatpel = kelasModel.getListMataPelajaran();
        addKelasResponse response = new addKelasResponse(kelasModel.getId(), kelasModel.getNamaKelas(), semesterId, usernameGuru,listStudent,listMatpel);
        //addKelasResponse response = new addKelasResponse(kelasModel.getId(),kelasModel.getNamaKelas(),semesterId,usernameGuru);
        return response;
    }


    //Add Matpel to particular class
    @Override
    public KelasModel addMatpelToKelas(String id, addMatpelKelasRequest[] listMatpel) {
        Optional<KelasModel> kelas = kelasDB.findById(Long.parseLong(id));
        KelasModel kelasObj = kelas.get();
        if (kelasObj.getListMataPelajaran() == null) {
            kelasObj.setListMataPelajaran(new ArrayList<MataPelajaranModel>());
        }
        for (int i = 0; i < listMatpel.length; i++) {
            //Optional<MataPelajaranModel> mataPelajaran = matpelService.getMatpelByName(listMatpel[i].getNamaMatpel());
            MataPelajaranModel mataPelajaran = matpelService.getMatpelById(listMatpel[i].getIdMatpel());
            //Long idMatpel = getMatpelRequest.getId();
            //MataPelajaranModel matpels = matpelService.getMatpelById(idMatpel);
            if (mataPelajaran != null) {
                kelasObj.getListMataPelajaran().add(mataPelajaran);
                if(mataPelajaran.getKelas() == null) {
                    mataPelajaran.setKelas(kelasObj);
                }

            }
        }
        kelasDB.save(kelasObj);
        return kelasObj;
    }

    //Add Murid to particular class
    @Override
    public List<siswaKelasResponse> addMuridToKelas(String idKelas, addMuridRequest[] username) {
        Optional<KelasModel> kelas = kelasDB.findById(Long.parseLong(idKelas));
        List<siswaKelasResponse> listResponse = new ArrayList<siswaKelasResponse>();
        KelasModel kelasObj = kelas.get();
        if (!(kelasObj.getListMataPelajaran() != null)) {
            kelasObj.setListMurid(new ArrayList<StudentModel>());
        }
        for (int i = 0; i < username.length; i++) {
            Optional<StudentModel> murid = studentService.getUserById(username[i].getUsername());
            StudentModel murids = murid.get();
            if (murids != null) {
                kelasObj.getListMurid().add(murids);
                // untuk setiap siswa nge loop di semua matpel yg ada di kelas ini
                // create student Matpel
                for (MataPelajaranModel matpel : kelasObj.getListMataPelajaran()) {
                    boolean hasPeminatan = false;
                    // Check if the student already has the peminatan
                    for (PeminatanModel peminatan : murids.getListPeminatan()) {
                        if (peminatan.getId() == matpel.getPeminatan().getId()) {
                            hasPeminatan = true;
                            break;
                        }
                    }
                    if (!hasPeminatan) {
                        // If the student doesn't have the peminatan, add it to the student and the peminatan
                        murids.getListPeminatan().add(matpel.getPeminatan());
                        matpel.getPeminatan().getListMurid().add(murids);
                    }
                    createStudentMatpel(murids, matpel);
                    if (matpel.getKelas() != null) {
                        for (KomponenModel komponen : matpel.getListKomponen()) {
                            komponenService.createStudentKomponen(murids, komponen);
                        }
                    }
                }
                if (murids.getListKelas() == null) {
                    murids.setListKelas(new ArrayList<KelasModel>());
                }
                murids.getListKelas().add(kelasObj);
            }
        }
        kelasDB.save(kelasObj);

        for (StudentModel siswa : kelasObj.getListMurid()) {
            siswaKelasResponse response = new siswaKelasResponse(siswa.getNama());
            listResponse.add(response);
        }

        return listResponse;
    }


    //Get List Siswa By Kelas
    @Override
    public List<siswaKelasResponse> getListSiswaByKelas(Long idKelas) {
        KelasModel kelas = kelasService.getById(idKelas);
        List<StudentModel> listStudentInKelas = kelas.getListMurid();
        List<siswaKelasResponse> responses= new ArrayList<siswaKelasResponse>();

        for(StudentModel student:listStudentInKelas){
            siswaKelasResponse response = new siswaKelasResponse(student.getNama());
            responses.add(response);
        }

        return responses;
    }

    //Retrieve Kelas By Id
    @Override
    public addKelasResponse getKelasById (Long idKelas){
        KelasModel kelas = kelasDB.findKelasById(idKelas);

        Long semesterId = kelas.getSemester().getId();
        String usernameGuru = kelas.getGuru().getUsername();
        List<StudentModel> listStudent = kelas.getListMurid();
        List<MataPelajaranModel> listMatpels = kelas.getListMataPelajaran();
        addKelasResponse response = new addKelasResponse(kelas.getId(),kelas.getNamaKelas(),semesterId,usernameGuru,listStudent,listMatpels);
        return response;

    }

    @Override
    public KelasModel getById (Long idKelas){
        Optional<KelasModel> kelas = kelasDB.findById(idKelas);
        if(kelas.isPresent()) {
            return kelas.get();
        } else{
            return null;
        }
    }

    //Retrieve Kelas By Guru
    @Override
    public List<kelasAllRequest> getListKelasByGuru(String usernameGuru) {
        GuruModel guru = guruService.getGuruByUsername(usernameGuru);
        List<KelasModel> listKelas = kelasDB.findKelasByGuru(guru);
        List<kelasAllRequest> listKelasRequest = new ArrayList<kelasAllRequest>();
        for(KelasModel kelas : listKelas) {
            kelasAllRequest tempKelas = new kelasAllRequest();
            tempKelas.setId(kelas.getId());
            tempKelas.setNamaKelas(kelas.getNamaKelas());
            tempKelas.setSemesterId(kelas.getSemester().getId());
            tempKelas.setNamaGuru(kelas.getGuru().getNama());
            listKelasRequest.add(tempKelas);
        }
        return listKelasRequest;
    }

    @Override
    public List<kelasAllRequest> getListKelasBySiswa(String usernameSiswa) {
        Optional<StudentModel> siswaRaw = studentService.getUserById(usernameSiswa);
        StudentModel siswa = siswaRaw.get();
        List<KelasModel> listKelasSiswa = kelasDB.findKelasBySiswa(siswa.getId());
        List<kelasAllRequest> listKelasRequest = new ArrayList<kelasAllRequest>();
        for(KelasModel kelas : listKelasSiswa) {
            kelasAllRequest tempKelas = new kelasAllRequest();
            tempKelas.setId(kelas.getId());
            tempKelas.setNamaKelas(kelas.getNamaKelas());
            tempKelas.setSemesterId(kelas.getSemester().getId());
            //tempKelas.setSemester(kelas.getSemester().getSemester());
            listKelasRequest.add(tempKelas);
        }
        return listKelasRequest;
    }


    @Override
    public List<StudentModel> getNotAssignedStudents() {
        List<StudentModel> allSiswa = studentService.getAllSiswa();
        List<StudentModel> notAssignedSiswa = new ArrayList<>();

        for (StudentModel student : allSiswa) {
            boolean isAssigned = isSiswaAssignedToClassThisSemester(student);
            if (!isAssigned) {
                notAssignedSiswa.add(student);
            }
        }

        return notAssignedSiswa;
    }

    public boolean isSiswaAssignedToClassThisSemester(StudentModel student) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDate today = LocalDate.now();
        String formattedDateToday = today.format(dateTimeFormatter);

        for (KelasModel kelas : student.getListKelas()) {
            LocalDateTime awalTahunAjaran = kelas.getSemester().getAwalTahunAjaran();
            String formattedAwalTahunAjaran = awalTahunAjaran.format(dateTimeFormatter);

            String[] arrOfStrTahunAjaran = formattedAwalTahunAjaran.split("/", 2);
            String[] arrOfStrToday = formattedDateToday.split("/", 2);

            if (arrOfStrTahunAjaran[1].equals(arrOfStrToday[1])) {
                if (Integer.valueOf(arrOfStrTahunAjaran[0]) <= Integer.valueOf(arrOfStrToday[0])) {
                    return true; // The student has been assigned to a class this semester
                }
            }
        }

        return false; // The student has not been assigned to any class this semester
    }


    @Override
    public List<StudentModel> getListSiswaInKelasX(String idKelas) {
        KelasModel kelas = kelasService.getById(Long.valueOf(idKelas));
        List<StudentModel> listSiswaInKelasX = kelas.getListMurid();
        return listSiswaInKelasX;
    }

    //Retrieve All Matpel yang belum di assign ke kelas manapun (method helper)
    @Override
    public List<MataPelajaranModel> getNotAssignedMatpel() {
        List<MataPelajaranModel> allMatpel = matpelService.getAllMatpel();
        return kelasService.getNotAssignedMatpel(allMatpel);
    }

    //Retrieve All Matpel yang belum di assign ke kelas manapun
    @Override
    public List<MataPelajaranModel> getNotAssignedMatpel(List<MataPelajaranModel> listMatpel) {
        List<MataPelajaranModel> listNotAssignedMatpel = new ArrayList<MataPelajaranModel>();
        for(MataPelajaranModel mataPelajaran : listMatpel) {
            KelasModel kelasSiswa = mataPelajaran.getKelas();
            if(kelasSiswa==null) {
                listNotAssignedMatpel.add(mataPelajaran);
            }

        }
        return listNotAssignedMatpel;
    }

    @Override
    public KelasModel getKelasCurrentSemester(String usernameMurid) {
        List<KelasModel> allKelasBelongingToSiswa = kelasService.getAllKelasSiswa(usernameMurid);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDate today = LocalDate.now();
        String formattedDateToday = today.format(formatterTime);
        KelasModel kelasModel = new KelasModel();

        //KelasModel kelasTerakhir = allKelasBelongingToSiswa.get(allKelasBelongingToSiswa.size() - 1);
        for(KelasModel kelas : allKelasBelongingToSiswa) {
            LocalDateTime akhirTahunAjaran = kelas.getSemester().getAkhirTahunAjaran();
            String formattedAkhirTahunAjaran = akhirTahunAjaran.format(formatterTime);
            String[] arrOfStrAkhirTahunAjaran = formattedAkhirTahunAjaran.split("/", 2);
            String[] arrOfStrToday = formattedDateToday.split("/", 2);
            if(arrOfStrAkhirTahunAjaran[1].equals(arrOfStrToday[1])){
                if(Integer.valueOf(arrOfStrAkhirTahunAjaran[0]) >= Integer.valueOf(arrOfStrToday[0])){
                    kelasModel = kelas;
                    return kelasModel;
                }
            }
        }

        return kelasModel;
    }


    @Override
    public kelasGuruResponse getKelasGuruCurrentSemester(String usernameGuru) {
        kelasAllRequest kelasModel = new kelasAllRequest();
        kelasGuruResponse kelasGuruResp = new kelasGuruResponse();
        List<kelasAllRequest> allKelasBelongingToGuru = kelasService.getListKelasByGuru(usernameGuru);

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDate today = LocalDate.now();
        String formattedDateToday = today.format(formatterTime);

        for(kelasAllRequest kelas : allKelasBelongingToGuru) {
            Long semesterId = kelas.getSemesterId();
            LocalDateTime akhirTahunAjaran = semesterService.getSemesterById(semesterId).getAkhirTahunAjaran();
            String formattedAkhirTahunAjaran = akhirTahunAjaran.format(formatterTime);
            String[] arrOfStrAkhirTahunAjaran = formattedAkhirTahunAjaran.split("/", 2);
            String[] arrOfStrToday = formattedDateToday.split("/", 2);
            if(arrOfStrAkhirTahunAjaran[1].equals(arrOfStrToday[1])){
                if(Integer.valueOf(arrOfStrAkhirTahunAjaran[0]) >= Integer.valueOf(arrOfStrToday[0])){
                    kelasGuruResp.setNamaGuru(guruService.getGuruByUsername(usernameGuru).getNama());
                    kelasGuruResp.setNamaKelas(kelas.getNamaKelas());
                    kelasGuruResp.setSemesterId(kelas.getSemesterId());
                    kelasGuruResp.setId(kelas.getId());
                    return kelasGuruResp;
                }
            }
        }

        return kelasGuruResp;
    }

    @Override
    public List<KelasModel> getAllKelasSiswa(String usernameMurid) {
        Optional<StudentModel> siswa = studentService.getUserById(usernameMurid);
        StudentModel siswaModel = siswa.get();
        List<KelasModel> allKelasBelongingToSiswa = siswaModel.getListKelas();
        return allKelasBelongingToSiswa;
    }

    @Override
    public List<kelasResponse> getAllKelasBySiswa(String usernameMurid) {
        Optional<StudentModel> siswa = studentService.getUserById(usernameMurid);
        StudentModel siswaModel = siswa.get();

        List<KelasModel> allKelasBelongingToSiswa = siswaModel.getListKelas();
        List<kelasResponse> response = new ArrayList<>();

        for(KelasModel kelas:allKelasBelongingToSiswa){
            kelasResponse responseKelas = new kelasResponse(kelas.getId(), kelas.getNamaKelas(), kelas.getSemester().getId(), kelas.getGuru().getUsername(), kelas.getListMurid(), kelas.getListMataPelajaran());
            response.add(responseKelas);
        }
        return response;
    }

    @Override
    public MataPelajaranModel getMatpelById(Long id) {
        // TODO Auto-generated method stub
        return matpelDB.findById(id);
    }

    // Create Student Mata Pelajaran Model
    @Override
    public StudentMataPelajaranModel createStudentMatpel(StudentModel student, MataPelajaranModel matpel) {
        StudentMataPelajaranModel studentMatpel = new StudentMataPelajaranModel();
        studentMatpel.setStudent(student);
        studentMatpel.setMatapelajaran(matpel);
        studentMatpel.setNilai_komponen(0);
        student.getNilaiAkhir().add(studentMatpel);
        return studentMatpelDB.save(studentMatpel);
    }

    @Override
    public boolean deleteClass(Long classId) {
        Optional<KelasModel> classOptional = kelasDB.findById(classId);

        if (classOptional.isPresent()) {
            KelasModel classModel = classOptional.get();
            kelasDB.delete(classModel);
            return true;
        }

        return false;
    }


}