package com.PASTRACK.PASTRACK.Service.MataPelajaran;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Repository.PeminatanDB;
import com.PASTRACK.PASTRACK.Repository.SemesterDB;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;

@Service
@Transactional
public class MatpelServiceImpl implements MatpelService {

    @Autowired
    private MatpelDB matpelDB;

    @Autowired
    private GuruService guruService;

    @Autowired
    private PeminatanDB peminatanDB;

    @Autowired
    private SemesterService semesterService;

    @Override
    public MataPelajaranModel getMatpelById(Long Id) {
        return matpelDB.findById(Id);
    }

    @Override
    public Optional<MataPelajaranModel> getMatpelByName(String namaMatpel) {
        return matpelDB.findByName(namaMatpel);
    }


    @Override
    public MataPelajaranModel createMatpel(String username, addMatpelRequest matpel) {
        GuruModel guru = guruService.getGuruByUsername(username);
        MataPelajaranModel matpelModel = new MataPelajaranModel();
        matpelModel.setNamaMataPelajaran(matpel.getNamaMataPelajaran());
        SemesterModel semester = semesterService.getSemesterById(Long.parseLong(matpel.getSemester()));
        matpelModel.setSemester(semester);
        PeminatanModel peminatan = peminatanDB.findById(Long.parseLong(matpel.getNamaPeminatan())).get();
        matpelModel.setPeminatan(peminatan);
        matpelModel.setDeskripsi(matpel.getDesc());
        matpelModel.setGuru(guru);
        guru.getListMataPelajaran().add(matpelModel);
        semester.getListMataPelajaran().add(matpelModel);
        
        if(peminatan.getListMataPelajaran() == null){
            peminatan.setListMataPelajaran(new ArrayList<MataPelajaranModel>());
        }
        peminatan.getListMataPelajaran().add(matpelModel);
        return matpelDB.save(matpelModel);
    }

    @Override
    public List<MatpelAllRequest> getListMatpelInGuru(String username) {
        // List<MataPelajnow = LocalDateTime.now();
        GuruModel guru = guruService.getGuruByUsername(username);
        List<MataPelajaranModel> listMatpel = matpelDB.findAllMatpelInGuru(guru, LocalDateTime.now());
        List<MatpelAllRequest> listMatpelRequest = new ArrayList<MatpelAllRequest>();
        for(MataPelajaranModel matpel : listMatpel) {
            MatpelAllRequest tempMatpel = new MatpelAllRequest();
            tempMatpel.setId(matpel.getId());
            tempMatpel.setNamaMataPelajaran(matpel.getNamaMataPelajaran());
            tempMatpel.setSemester(matpel.getSemester());
            tempMatpel.setDeskripsi(matpel.getDeskripsi());
            listMatpelRequest.add(tempMatpel);
        }
        return listMatpelRequest;
    }

    @Override
    public List<MataPelajaranModel> getListMatpelByGuru(String usernameGuru) {
        GuruModel guru = guruService.getGuruByUsername(usernameGuru);
        List<MataPelajaranModel> listMatpel = matpelDB.findAllMatpelInGuru(guru, LocalDateTime.now());
        return listMatpel;
    }

    @Override
    public List<MataPelajaranModel> getAllMatpel() {
        List<MataPelajaranModel> listMatpel = matpelDB.findAll();
        return listMatpel;
    }

    @Override
    public Long getIdMatpel(addMatpelKelasRequest matpel) {
        Long idMatpel = matpelDB.findIdMatpel();
        return idMatpel;
    }

    @Override
    public List<SemesterModel> getAllSemester() {
        List<SemesterModel> listSemester = semesterService.findAll();
        return listSemester;
    }

    @Override
    public List<MatpelAllRequest> getListAllMatpelInGuru(String username) {
        GuruModel guru = guruService.getGuruByUsername(username);
        List<MataPelajaranModel> listMatpelModel = matpelDB.findListAllMatpelInGuru(guru);
        List<MatpelAllRequest> listMatpelRequest = new ArrayList<MatpelAllRequest>();
        for(MataPelajaranModel matpel : listMatpelModel) {
            MatpelAllRequest tempMatpel = new MatpelAllRequest();
            tempMatpel.setId(matpel.getId());
            tempMatpel.setNamaMataPelajaran(matpel.getNamaMataPelajaran());
            tempMatpel.setSemester(matpel.getSemester());
            tempMatpel.setDeskripsi(matpel.getDeskripsi());
            listMatpelRequest.add(tempMatpel);
        }
        return listMatpelRequest;
    }
}
