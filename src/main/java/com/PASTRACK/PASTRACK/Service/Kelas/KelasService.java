package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.List;

import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;


public interface KelasService {
    
    KelasModel createKelas(addKelasRequest kelas);

    List<KelasModel> getAllKelas();

    KelasModel addMuridToKelas (String id, addMuridRequest[] username);
    KelasModel addMatpelToKelas (String id, addMatpelKelasRequest[] listMatpel);
    KelasModel getKelasById (Long idKelas);
    List<kelasAllRequest> getListKelasByGuru(String username);
   
}