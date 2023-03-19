package com.PASTRACK.PASTRACK.Service.StudentKomponen;

import java.util.Optional;

import com.PASTRACK.PASTRACK.Model.StudentKomponenModel;

public interface StudentKomponenService {
    Optional<StudentKomponenModel> getById(Long id);
}
