package com.PASTRACK.PASTRACK.Service.Peminatan;

import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanRequest;

import java.util.List;

public interface PeminatanService {
    PeminatanModel getPeminatanByNama(String namaPeminatan);
    List<PeminatanRequest> getAllPeminatan();
    PeminatanModel getPeminatanById(String idPeminatan);
}
