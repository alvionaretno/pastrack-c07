package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Service.MataPelajaran.MatpelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.Repository.KelasDB;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import com.PASTRACK.PASTRACK.Service.User.UserService;

@Service
@Transactional
public class KelasServiceImpl implements KelasService {

    @Autowired
    private KelasDB kelasDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MatpelService matpelService;

    @Override
    public KelasModel addKelas(KelasModel kelas) {
        kelasDB.save(kelas);
        return kelas;
    }

    @Override
    public KelasModel addMurid(String id, addMuridRequest[] username) {
        // TODO Auto-generated method stub
        Optional<KelasModel> kelas = kelasDB.findById(Long.parseLong(id));
        KelasModel kelasObj = kelas.get();
        if (!(kelasObj.getListMataPelajaran() != null)) {
            kelasObj.setListMurid(new ArrayList<StudentModel>());
        }
        for (int i = 0; i < username.length; i++) {
            Optional<StudentModel> murid = studentService.getUserById(username[i].getUsername());
            if (murid != null) {
                kelasObj.getListMurid().add(murid.get());
            }
        }
        kelasDB.save(kelasObj);
        return kelasObj;
    }

    @Override
    public KelasModel addMatpel(String id, addMatpelRequest[] listMatpel) {
        // TODO Auto-generated method stub
        Optional<KelasModel> kelas = kelasDB.findById(Long.parseLong(id));
        KelasModel kelasObj = kelas.get();
        if (kelasObj.getListMataPelajaran() == null) {
            kelasObj.setListMataPelajaran(new ArrayList<MataPelajaranModel>());
        }
        for (int i = 0; i < listMatpel.length; i++) {
            //Optional<MataPelajaranModel> matpel = matpelService.getNamaMatpelById(listMatpel[i].get);
            //if (murid != null) {
            //    kelasObj.getListMurid().add(murid.get());
            //}
        }
        kelasDB.save(kelasObj);
        return kelasObj;
    }
}
