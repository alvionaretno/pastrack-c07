package com.PASTRACK.PASTRACK.Service.Peminatan;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Repository.PeminatanDB;

@Service
@Transactional
public class PeminatanServiceImpl implements PeminatanService {
    @Autowired
    private PeminatanDB peminatanDB;

    @Override
    public PeminatanModel getPeminatanByNama(String namaPeminatan) {
        return peminatanDB.findByNamaPeminatan(namaPeminatan);
    }
}
