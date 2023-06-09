package com.PASTRACK.PASTRACK.Service.Komponen;

import java.util.List;

import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenResponse;
import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.KomponenRequest.listKomponenSiswaResponse;
import com.PASTRACK.PASTRACK.Model.KomponenModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentKomponenModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;


public interface KomponenService {
    KomponenModel getKomponenByKode(Long kode);
    addKomponenResponse createKomponen(String id, addKomponenRequest komponen);
    KomponenModel updateKomponen(String idMatpel, String idKomponen, addKomponenRequest komponenModel);
    addKomponenRequest readKomponen(String kode);
    getComponent getKomponen( StudentKomponenModel studentKomponen);
    List<getComponent> getListKomponen(StudentModel student, MataPelajaranModel matpel);
    KomponenModel updateStudentKomponen(int bobot, Long kodeKomponen);
    StudentKomponenModel createStudentKomponen(StudentModel student, KomponenModel komponen);
    listKomponenSiswaResponse getListKomponenNilaiAkhir(StudentModel student, MataPelajaranModel matpel);
}
