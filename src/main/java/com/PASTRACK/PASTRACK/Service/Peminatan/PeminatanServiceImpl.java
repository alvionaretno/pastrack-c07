package com.PASTRACK.PASTRACK.Service.Peminatan;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanRequest;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanResponse;
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
    public List<PeminatanResponse> getAllPeminatan() {
        List<PeminatanResponse> allPeminatan = new ArrayList<>();
        List<PeminatanModel> listPeminatan = peminatanDB.findAll();
        for (PeminatanModel minatModel : listPeminatan) {
            PeminatanResponse current= new PeminatanResponse(minatModel.getId(), minatModel.getNamaPeminatan());
            // current.setId(minatModel.getId());
            // current.setNamaPeminatan(minatModel.getNamaPeminatan());
            allPeminatan.add(current);
        }
        return allPeminatan;
    }

    @Override
    public PeminatanModel getPeminatanById(String idPeminatan) {
        Optional<PeminatanModel> x = peminatanDB.findById(Long.parseLong(idPeminatan));
        PeminatanModel peminatan = x.get();
        return peminatan;
    }

    @Override
    public PeminatanModel createPeminatan(PeminatanRequest peminatanReq) {
        PeminatanModel peminatan = new PeminatanModel();
        peminatan.setNamaPeminatan(peminatanReq.getNamaPeminatan());
        return peminatanDB.save(peminatan);
    }
}
