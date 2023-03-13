package com.PASTRACK.PASTRACK.Service.Student;

import java.util.List;
import java.util.Optional;

import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.UserRequest;



public interface StudentService {
    Optional<StudentModel> getUserById(String username);
}