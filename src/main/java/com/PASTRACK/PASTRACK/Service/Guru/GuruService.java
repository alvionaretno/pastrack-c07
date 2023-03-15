package com.PASTRACK.PASTRACK.Service.Guru;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.GuruModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;

public interface GuruService {
    GuruModel getGuruByUsername(String username);
    List<MataPelajaranModel> listMatpelDiGuru(String id);
}
