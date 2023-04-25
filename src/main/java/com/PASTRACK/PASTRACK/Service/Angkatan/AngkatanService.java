package com.PASTRACK.PASTRACK.Service.Angkatan;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.AngkatanModel;

public interface AngkatanService {
    List<AngkatanModel> findAll();
    AngkatanModel getAngkatanById(Long id);

}