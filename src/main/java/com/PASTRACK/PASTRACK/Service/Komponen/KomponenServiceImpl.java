package com.PASTRACK.PASTRACK.Service.Komponen;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.Service.StudentKomponen.StudentKomponenService;
import com.PASTRACK.PASTRACK.Service.StudentMatpel.StudentMatpelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenResponse;
import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.KomponenRequest.listKomponenSiswaResponse;
import com.PASTRACK.PASTRACK.Model.KomponenModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Model.StudentKomponenModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Repository.KomponenDB;
import com.PASTRACK.PASTRACK.Repository.SemesterDB;
import com.PASTRACK.PASTRACK.Repository.StudentDB;
import com.PASTRACK.PASTRACK.Repository.StudentKomponenDB;
import com.PASTRACK.PASTRACK.Repository.StudentMatpelDB;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;

@Service
@Transactional
public class KomponenServiceImpl implements KomponenService {
    @Autowired
    private KomponenDB komponenDB;

    @Autowired
    private MatpelService matpelService;

    @Autowired
    private StudentKomponenService studentKomponenService;
    
    @Autowired
    private StudentKomponenDB studentKomponenDB;

    @Autowired
    private StudentDB studentDB;

    @Autowired
    private StudentMatpelDB studentMatpelDB;

    @Autowired
    private StudentMatpelService studentMatpelService;
    
    @Override
    public KomponenModel getKomponenByKode(Long kode) {
        return komponenDB.findByKode(kode);
    }

    @Override
    public addKomponenResponse createKomponen(String id, addKomponenRequest komponenReq) {
        MataPelajaranModel matpelModel = matpelService.getMatpelById(Long.parseLong(id));
        KomponenModel komponenModel = new KomponenModel();
        komponenModel.setTitle(komponenReq.getNamaKomponen());
        komponenModel.setDescription(komponenReq.getDesc());
        komponenModel.setIsReleased(false);
        komponenModel.setDueDate(komponenReq.getDueDate().atStartOfDay());
        komponenModel.setBobot(komponenReq.getBobot());
        //komponenModel.setNilaiComponent(0);
        // komponenModel.setAkhirTahunAjaran(matpelModel.getAkhirTahunAjaran());
        // komponenModel.setAwalTahunAjaran(matpelModel.getAwalTahunAjaran());
        komponenModel.setMatapelajaran(matpelModel);
        komponenModel.setSemester(matpelModel.getSemester());
        matpelModel.getListKomponen().add(komponenModel);
        if (matpelModel.getKelas() != null && matpelModel.getKelas().getListMurid() != null) {
            for (StudentModel student : matpelModel.getKelas().getListMurid()) {
                createStudentKomponen(student, komponenModel);
            }
        }
        komponenDB.save(komponenModel);
        // response
        Long idKomponen = komponenModel.getKode();
        String namaKomponen = komponenModel.getTitle();
        String desc = komponenModel.getDescription();
        LocalDateTime dueDate = komponenModel.getDueDate();
        int bobot = komponenModel.getBobot();
        return new addKomponenResponse(idKomponen, namaKomponen, desc, dueDate, bobot);
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
    public KomponenModel updateStudentKomponen(int nilai,  Long komponenId) {
        // TODO Auto-generated method stub
        Optional<StudentKomponenModel> studentKomponenOp = studentKomponenService.getById(komponenId);
        StudentKomponenModel studentKomponen = studentKomponenOp.get();
        studentKomponen.setNilaiKomponen(nilai);
        KomponenModel komponenModel = getKomponenByKode(komponenId);
        //komponenModel.setNilaiComponent(nilai);
        studentKomponenDB.save(studentKomponen);
        StudentMataPelajaranModel sm = studentMatpelService.getStudentMatpel(studentKomponen.getStudent(), studentKomponen.getKomponen().getMatapelajaran());
        studentMatpelService.generateNilaiStudentMatpel(sm);
        studentMatpelDB.save(sm);
        return komponenModel;
    }

    @Override
    public StudentKomponenModel createStudentKomponen(StudentModel student, KomponenModel komponen) {
        StudentKomponenModel studentKomponen = new StudentKomponenModel();
        studentKomponen.setKomponen(komponen);
        studentKomponen.setStudent(student);
        //baru
        studentKomponen.setNilaiKomponen(0);
        student.getNilai().add(studentKomponen);
        return studentKomponenDB.save(studentKomponen);
    }

    @Override
    public listKomponenSiswaResponse getListKomponenNilaiAkhir(StudentModel student, MataPelajaranModel matpel) {
        listKomponenSiswaResponse responseKomponen = new listKomponenSiswaResponse();
        responseKomponen.setListKomponenSiswa(getListKomponen(student, matpel));
        StudentMataPelajaranModel sm = studentMatpelService.getStudentMatpel(student, matpel);
        responseKomponen.setNilaiAkhir(sm.getNilai_komponen());
        return responseKomponen;
    }
}
