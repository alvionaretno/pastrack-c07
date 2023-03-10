package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;

// import apap.project.rumahsehat.Model.AdminModel;
// import apap.project.rumahsehat.Model.ApotekerModel;
// import apap.project.rumahsehat.Model.DokterModel;
// import apap.project.rumahsehat.Model.PasienModel;
// import apap.project.rumahsehat.Model.UserModel;

public interface KelasService {
    
    KelasModel addKelas(KelasModel kelas);
    KelasModel addMurid (String id, addMuridRequest[] username);
   
}