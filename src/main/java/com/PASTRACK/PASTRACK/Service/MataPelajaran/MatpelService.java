package com.PASTRACK.PASTRACK.Service.MataPelajaran;
import java.util.List;
import java.util.Optional;

import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;

public interface MatpelService {
    MataPelajaranModel getMatpelById(Long Id);
    MataPelajaranModel createMatpel(String id, addMatpelRequest matpel);
    // List<MataPelajaranModel> getListMatpel();
    List<MatpelAllRequest> getListMatpelInGuru(String username);
    //MataPelajaranModel getMatpelByName(String namaMatpel);

    List<MataPelajaranModel> getAllMatpel();

    Optional<MataPelajaranModel> getMatpelByName(String namaMatpel);

    Long getIdMatpel(addMatpelKelasRequest matpel);
}
