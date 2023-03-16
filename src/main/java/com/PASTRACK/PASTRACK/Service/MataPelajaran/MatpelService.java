package com.PASTRACK.PASTRACK.Service.MataPelajaran;
import java.util.List;

import com.PASTRACK.PASTRACK.MatpelRequest.MatpelAllRequest;
import com.PASTRACK.PASTRACK.MatpelRequest.addMatpelRequest;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;

public interface MatpelService {
    MataPelajaranModel getMatpelById(Long Id);
    MataPelajaranModel createMatpel(String id, addMatpelRequest matpel);
    // List<MataPelajaranModel> getListMatpel();
    List<MatpelAllRequest> getListMatpelInGuru(String username);
}
