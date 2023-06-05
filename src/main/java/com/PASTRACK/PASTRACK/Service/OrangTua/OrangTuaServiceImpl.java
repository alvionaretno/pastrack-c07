package com.PASTRACK.PASTRACK.Service.OrangTua;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PASTRACK.PASTRACK.Model.OrangTuaModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.OrangTuaRequest.OrangTuaRequest;
import com.PASTRACK.PASTRACK.Repository.OrangTuaDB;
import com.PASTRACK.PASTRACK.Repository.UserDB;

@Service
@Transactional
public class OrangTuaServiceImpl implements OrangTuaService {

    @Autowired
    private OrangTuaDB orangTuaDB;

    @Override
    public List<OrangTuaRequest> getUsernameSiswa(String usernameOrtu) {
        Optional<OrangTuaModel> ortu = orangTuaDB.findByUsername(usernameOrtu);
        OrangTuaModel ortuModel = ortu.get();
        List<StudentModel> listAnak = ortuModel.getAnak();
        List<OrangTuaRequest> usernameAnak = new ArrayList<>();
        for (StudentModel student : listAnak) {
            OrangTuaRequest ortuReq = new OrangTuaRequest();
            ortuReq.setUSernameAnak(student.getUsername());
            usernameAnak.add(ortuReq);
        }
        return usernameAnak;
    }

}
