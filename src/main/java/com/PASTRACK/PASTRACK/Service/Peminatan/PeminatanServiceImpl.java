package com.PASTRACK.PASTRACK.Service.Peminatan;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanRequest;
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

    @Override
    public List<PeminatanRequest> getAllPeminatan() {
        List<PeminatanRequest> allPeminatan = new ArrayList<>();
        List<PeminatanModel> listPeminatan = peminatanDB.findAll();
        for (PeminatanModel minatModel : listPeminatan) {
            PeminatanRequest current= new PeminatanRequest();
            current.setId(minatModel.getId());
            current.setNamaPeminatan(minatModel.getNamaPeminatan());
            allPeminatan.add(current);
        }
        return allPeminatan;
    }
}
