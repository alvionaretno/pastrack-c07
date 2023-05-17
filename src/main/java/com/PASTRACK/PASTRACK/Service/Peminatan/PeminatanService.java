package com.PASTRACK.PASTRACK.Service.Peminatan;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanRequest;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanResponse;

import java.util.List;

public interface PeminatanService {
    PeminatanModel getPeminatanByNama(String namaPeminatan);
    List<PeminatanResponse> getAllPeminatan();
    PeminatanModel getPeminatanById(String idPeminatan);
    PeminatanModel createPeminatan(PeminatanRequest peminatanReq);
    List<PeminatanModel> getAllPeminatanModel();
    List<PeminatanResponse> getListPeminatanInSiswa(String username);
}
