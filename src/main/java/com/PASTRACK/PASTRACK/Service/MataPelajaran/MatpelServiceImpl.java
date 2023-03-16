package com.PASTRACK.PASTRACK.Service.MataPelajaran;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Repository.GuruDB;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Repository.PeminatanDB;
import com.PASTRACK.PASTRACK.Service.Guru.GuruService;

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
    private GuruDB guruDB;

    @Override
    public MataPelajaranModel getMatpelById(Long Id) {
        return matpelDB.findById(Id);
    }

    @Override
    public MataPelajaranModel createMatpel(String username, addMatpelRequest matpel) {
        GuruModel guru = guruService.getGuruByUsername(username);
        MataPelajaranModel matpelModel = new MataPelajaranModel();
        matpelModel.setNamaMataPelajaran(matpel.getNamaMataPelajaran());
        if (matpel.getSemester().equals("GENAP")) {
            matpelModel.setSemester(false);
        } else {
            matpelModel.setSemester(true);
        }
        PeminatanModel peminatan = peminatanDB.findByNamaPeminatan(matpel.getNamaPeminatan());
        matpelModel.setPeminatan(peminatan);
        matpelModel.setGuru(guru);
        matpelModel.setAwalTahunAjaran(matpel.getAwalTahunAjaran());
        matpelModel.setAkhirTahunAjaran(matpel.getAkhirTahunAjaran());
        guru.getListMataPelajaran().add(matpelModel);
        // guruDB.save(guru);
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
            tempMatpel.setNamaMataPelajaran(matpel.getNamaMataPelajaran());
            tempMatpel.setSemester(matpel.getSemester());
            tempMatpel.setDeskripsi(matpel.getDeskripsi());
            tempMatpel.setAwalTahunAjaran(matpel.getAwalTahunAjaran());
            tempMatpel.setAkhirTahunAjaran(matpel.getAkhirTahunAjaran());
            listMatpelRequest.add(tempMatpel);
        }
        return listMatpelRequest;
    }
}