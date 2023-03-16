package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.List;

import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;


public interface KelasService {
    
    KelasModel addKelas(KelasModel kelas);
    KelasModel addMurid (String id, addMuridRequest[] username);
    KelasModel addMatpel (String id, addMatpelRequest[] username);
   
}