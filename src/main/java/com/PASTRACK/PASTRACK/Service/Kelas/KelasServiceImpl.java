package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.Model.*;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
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
    private StudentService studentService;

    @Autowired
    private GuruService guruService;

    @Autowired
    private MatpelService matpelService;

    @Autowired
    private KelasService kelasService;

    @Override
    public List<KelasModel> getAllKelas() {
        List<KelasModel> listKelas = kelasDB.findAll();
        return listKelas;
    }

    @Override
    public KelasModel createKelas(addKelasRequest kelas) {
        KelasModel kelasModel = new KelasModel();
        System.out.println(kelas);
        kelasModel.setNamaKelas(kelas.getNamaKelas());
        if (kelas.getSemester().equals("GENAP")) {
            kelasModel.setSemester(false);
        } else {
            kelasModel.setSemester(true);
        }
        GuruModel guru = guruService.getGuruByUsername(kelas.getUsernameGuru());
        kelasModel.setGuru(guru);
        kelasModel.setAwalTahunAjaran(kelas.getAwalTahunAjaran().atStartOfDay());
        kelasModel.setAkhirTahunAjaran(kelas.getAkhirTahunAjaran().atStartOfDay());
        guru.getListKelas().add(kelasModel);
        return kelasDB.save(kelasModel);
    }

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

    @Override
    public KelasModel addMatpelToKelas(String id, addMatpelKelasRequest[] listMatpel) {
        Optional<KelasModel> kelas = kelasDB.findById(Long.parseLong(id));
        KelasModel kelasObj = kelas.get();
        if (kelasObj.getListMataPelajaran() == null) {
            kelasObj.setListMataPelajaran(new ArrayList<MataPelajaranModel>());
        }
        for (int i = 0; i < listMatpel.length; i++) {
            Optional<MataPelajaranModel> mataPelajaran = matpelService.getMatpelByName(listMatpel[i].getNamaMatpel());
            MataPelajaranModel matpels = mataPelajaran.get();
            if (matpels != null) {
                kelasObj.getListMataPelajaran().add(matpels);
                if(matpels.getKelas() == null) {
                    matpels.setKelas(kelasObj);
                }

            }
        }
        kelasDB.save(kelasObj);
        return kelasObj;
    }

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

    @Override
    public List<kelasAllRequest> getListKelasByGuru(String usernameGuru) {
        GuruModel guru = guruService.getGuruByUsername(usernameGuru);
        List<KelasModel> listKelas = kelasDB.findKelasByGuru(guru);
        List<kelasAllRequest> listKelasRequest = new ArrayList<kelasAllRequest>();
        for(KelasModel kelas : listKelas) {
            kelasAllRequest tempKelas = new kelasAllRequest();
            tempKelas.setId(kelas.getId());
            tempKelas.setNamaKelas(kelas.getNamaKelas());
            tempKelas.setSemester(kelas.getSemester());
            tempKelas.setAwalTahunAjaran(kelas.getAwalTahunAjaran());
            tempKelas.setAkhirTahunAjaran(kelas.getAkhirTahunAjaran());
            listKelasRequest.add(tempKelas);
        }
        return listKelasRequest;
    }
}
