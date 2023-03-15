package com.PASTRACK.PASTRACK.Service.Guru;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Repository.GuruDB;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDB guruDB;

    @Override
    public GuruModel getGuruByUsername(String username) {
        return guruDB.findByUsername(username);
    }

    @Override
    public List<MataPelajaranModel> listMatpelDiGuru(String username) {
        GuruModel guru = guruDB.findByUsername(username);
        List<MataPelajaranModel> listMatpel = guru.getListMataPelajaran();
        return listMatpel;
    }
}
