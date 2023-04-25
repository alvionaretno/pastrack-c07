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
import com.PASTRACK.PASTRACK.Repository.StudentKomponenDB;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
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
    private StudentService studentService;

    @Autowired
    private GuruService guruService;

    @Autowired
    private MatpelService matpelService;

    @Autowired
    private KelasService kelasService;

    @Autowired
    private SemesterService semesterService;

    //Retrieve All Kelas
    @Override
    public List<KelasModel> getAllKelas() {
        List<KelasModel> listKelas = kelasDB.findAll();
        return listKelas;
    }

    //Create Kelas
    @Override
    public KelasModel createKelas(addKelasRequest kelas) {
        KelasModel kelasModel = new KelasModel();
        System.out.println(kelas);
        kelasModel.setNamaKelas(kelas.getNamaKelas());
        kelasModel.setSemester(semesterService.getSemesterById(kelas.getSemesterId()));
        GuruModel guru = guruService.getGuruByUsername(kelas.getUsernameGuru());
        kelasModel.setGuru(guru);
        guru.getListKelas().add(kelasModel);
        return kelasDB.save(kelasModel);
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
    public KelasModel addMuridToKelas(String idKelas, addMuridRequest[] username) {
        Optional<KelasModel> kelas = kelasDB.findById(Long.parseLong(idKelas));
        KelasModel kelasObj = kelas.get();
        if (!(kelasObj.getListMataPelajaran() != null)) {
            kelasObj.setListMurid(new ArrayList<StudentModel>());
        }
        for (int i = 0; i < username.length; i++) {
            Optional<StudentModel> murid = studentService.getUserById(username[i].getUsername());
            StudentModel murids = murid.get();
            if (murids != null) {
                kelasObj.getListMurid().add(murids);
                if(murids.getListKelas() == null) {
                    murids.setListKelas(new ArrayList<KelasModel>());
                }
                murids.getListKelas().add(kelasObj);

            }
        }
        kelasDB.save(kelasObj);
        return kelasObj;
    }

    //Retrieve Kelas By Id
    @Override
    public KelasModel getKelasById (Long IdKelas){
        Optional<KelasModel> kelas = kelasDB.findById(IdKelas);
        if (kelas.isPresent()){
            return kelas.get();
        }
        else {
            throw new NoSuchElementException();
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
            //tempKelas.setSemester(kelas.getSemester().getSemester());
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


    //Checking if certain Siswa is already assigned to particular class in current semester
    @Override
    public Boolean cekIfSiswaHasBeenAssigned(List<KelasModel> listKelasInSiswa) {
        //List<kelasAllRequest> listKelasSiswa = new ArrayList<kelasAllRequest>();
        Boolean hasBeenAssigned = false;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDate today = LocalDate.now();
        String formattedDateToday = today.format(dateTimeFormatter);

        for(KelasModel kelas : listKelasInSiswa) {
            //LocalDateTime awalTahunAjaran = kelas.getAwalTahunAjaran();
            //String formattedAwalTahunAjaran = awalTahunAjaran.format(dateTimeFormatter);
            //String[] arrOfStrTahunAjaran = formattedAwalTahunAjaran.split("/", 2);
            LocalDateTime awalTahunAjaran = kelas.getSemester().getAwalTahunAjaran();
            String formattedAwalTahunAjaran = awalTahunAjaran.format(dateTimeFormatter);
            String[] arrOfStrTahunAjaran = formattedAwalTahunAjaran.split("/", 2);
            String[] arrOfStrToday = formattedDateToday.split("/", 2);
            if(arrOfStrTahunAjaran[1].equals(arrOfStrToday[1])){
                if(Integer.valueOf(arrOfStrTahunAjaran[0]) <= Integer.valueOf(arrOfStrToday[0])){
                   hasBeenAssigned = true;
                }
            }

        }
        return hasBeenAssigned;
    }

    //Retrieve All Siswa yang belum di assign ke kelas manapun (method helper)
    @Override
    public List<StudentModel> getNotAssignedStudents() {
        List<StudentModel> allSiswa = studentService.getAllSiswa();
        return kelasService.getNotAssignedStudents(allSiswa);
    }

    //Retrieve All Siswa yang belum di assign ke kelas manapun
    @Override
    public List<StudentModel> getNotAssignedStudents(List<StudentModel> listSiswa) {
        List<StudentModel> listNotAssignedSiswa = new ArrayList<StudentModel>();
        for(StudentModel student : listSiswa) {
            List<KelasModel> kelasSiswa = student.getListKelas();
            Boolean isAssigned = kelasService.cekIfSiswaHasBeenAssigned(kelasSiswa);
            if(isAssigned==false) {
                listNotAssignedSiswa.add(student);
            }

        }
        return listNotAssignedSiswa;
    }

    @Override
    public List<StudentModel> getListSiswaInKelasX(String idKelas) {
        KelasModel kelas = kelasService.getKelasById(Long.valueOf(idKelas));
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
    public MataPelajaranModel getMatpelById(Long id) {
        // TODO Auto-generated method stub
        return matpelDB.findById(id);
    }

}
