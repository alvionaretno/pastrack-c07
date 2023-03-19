package com.PASTRACK.PASTRACK.Service.Komponen;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.Model.KomponenModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentKomponenModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Repository.KomponenDB;

import com.PASTRACK.PASTRACK.Repository.StudentKomponenDB;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;

@Service
@Transactional
public class KomponenServiceImpl implements KomponenService {
    @Autowired
    private KomponenDB komponenDB;

    @Autowired
    private MatpelService matpelService;
    
    @Autowired
    private StudentKomponenDB studentKomponenDB;
    
    @Override
    public KomponenModel getKomponenByKode(Long kode) {
        return komponenDB.findByKode(kode);
    }

    @Override
    public KomponenModel createKomponen(String id, addKomponenRequest komponenReq) {
        MataPelajaranModel matpelModel = matpelService.getMatpelById(Long.parseLong(id));
        KomponenModel komponenModel = new KomponenModel();
        komponenModel.setTitle(komponenReq.getNamaKomponen());
        komponenModel.setDescription(komponenReq.getDesc());
        komponenModel.setIsReleased(false);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        // LocalDate time = LocalDate.parse(komponenReq.getDueDate(), formatter);
        komponenModel.setDueDate(komponenReq.getDueDate().atStartOfDay());
        komponenModel.setBobot(komponenReq.getBobot());
        komponenModel.setAkhirTahunAjaran(matpelModel.getAkhirTahunAjaran());
        komponenModel.setAwalTahunAjaran(matpelModel.getAwalTahunAjaran());
        komponenModel.setMatapelajaran(matpelModel);
        matpelModel.getListKomponen().add(komponenModel);
        // matpelDB.save(matpelModel);
        // System.out.println("berhasil");
        return komponenDB.save(komponenModel);
    }

    @Override
    public KomponenModel updateKomponen(String idMatpel, String kodeKomponen, addKomponenRequest komponenRequest) {
        KomponenModel updateKomponen = komponenDB.findByKode(Long.parseLong(kodeKomponen));
        updateKomponen.setTitle(komponenRequest.getNamaKomponen());
        updateKomponen.setDescription(komponenRequest.getDesc());
        updateKomponen.setDueDate(komponenRequest.getDueDate().atStartOfDay());
        updateKomponen.setBobot(komponenRequest.getBobot());
        return komponenDB.save(updateKomponen);
    }

    @Override
    public addKomponenRequest readKomponen(String kodeKomponen) {
        KomponenModel komponenModel = komponenDB.findByKode(Long.parseLong(kodeKomponen));
        addKomponenRequest komponenReq = new addKomponenRequest();
        komponenReq.setNamaKomponen(komponenModel.getTitle());
        komponenReq.setDueDate(komponenModel.getDueDate().toLocalDate());
        komponenReq.setBobot(komponenModel.getBobot());
        komponenReq.setDesc(komponenModel.getDescription());
        return komponenReq;
    }

    @Override
    public getComponent getKomponen(StudentKomponenModel studentKomponen) {
        // TODO Auto-generated method stub
        return new getComponent(studentKomponen.getId(), studentKomponen.getKomponen().getTitle(), studentKomponen.getKomponen().getBobot(), studentKomponen.getNilaiKomponen());

        // throw new UnsupportedOperationException("Unimplemented method 'getKomponen'");
    }

    @Override
    public List<getComponent> getListKomponen(StudentModel student, MataPelajaranModel matpel) {
        // TODO Auto-generated method stub
        return komponenDB.getAllKomponenSiswa(student, matpel);
    }

    @Override
    public StudentKomponenModel updateStudentKomponen(StudentKomponenModel studentKomponen, int nilai) {
        // TODO Auto-generated method stub
        studentKomponen.setNilaiKomponen(nilai);
        return studentKomponenDB.save(studentKomponen);
    }
}
