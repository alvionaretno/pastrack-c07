package com.PASTRACK.PASTRACK.Service.Komponen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
import com.PASTRACK.PASTRACK.Model.KomponenModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Repository.KomponenDB;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;

@Service
@Transactional
public class KomponenServiceImpl implements KomponenService {
    @Autowired
    private KomponenDB komponenDB;

    @Autowired 
    private MatpelDB matpelDB;

    @Autowired
    private MatpelService matpelService;
    
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
        MataPelajaranModel matpelModel = matpelDB.findById(Long.parseLong(idMatpel));
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
}
