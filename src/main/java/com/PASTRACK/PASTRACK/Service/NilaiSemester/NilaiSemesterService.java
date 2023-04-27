package com.PASTRACK.PASTRACK.Service.NilaiSemester;
import com.PASTRACK.PASTRACK.Model.NilaiSemesterModel;

import java.util.List;


public interface NilaiSemesterService {
    List<NilaiSemesterModel> findAll();
    NilaiSemesterModel getById(Long id);

}