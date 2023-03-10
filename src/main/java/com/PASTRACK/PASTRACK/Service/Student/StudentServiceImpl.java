package com.PASTRACK.PASTRACK.Service.Student;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.Repository.StudentDB;
import com.PASTRACK.PASTRACK.Service.User.UserService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentDB studentDB;
    

    @Override
    public Optional<StudentModel> getUserById(String username) {
        // TODO Auto-generated method stub
        UserModel user = userService.getUserByUsername(username);
        return studentDB.findById(user.getId());
    }
    
}
