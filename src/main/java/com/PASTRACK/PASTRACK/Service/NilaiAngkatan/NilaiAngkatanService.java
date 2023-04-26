package com.PASTRACK.PASTRACK.Service.NilaiAngkatan;

import com.PASTRACK.PASTRACK.Model.NilaiAngkatanModel;

import java.util.List;


public interface NilaiAngkatanService {
    List<NilaiAngkatanModel> findAll();
    NilaiAngkatanModel getById(Long id);

}