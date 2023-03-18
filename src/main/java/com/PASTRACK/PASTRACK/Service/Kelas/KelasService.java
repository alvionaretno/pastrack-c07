package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.List;

import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.kelasMatpelRequest.addMatpelToKelasRequest;


public interface KelasService {
    
    KelasModel addKelas(KelasModel kelas);
    KelasModel addMuridToKelas (String id, addMuridRequest[] username);
    KelasModel addMatpelToKelas (String id, addMatpelToKelasRequest[] listMatpel);
    KelasModel getKelasById (Long idKelas);
    List<kelasAllRequest> getListKelasByGuru(String username);
   
}