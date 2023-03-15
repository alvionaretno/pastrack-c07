package com.PASTRACK.PASTRACK.Service.Postingan;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Model.UserModel;
import com.PASTRACK.PASTRACK.Repository.PostinganDB;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;
import com.PASTRACK.PASTRACK.Service.User.UserService;

@Service
@Transactional
public class PostinganServiceImpl implements PostinganService {

    @Autowired
    private PostinganDB postinganDB;

    @Autowired
    private StudentService studentService;

    @Override
    public PostinganTugasModel addPostingan(PostinganTugasModel postingan) {
        postinganDB.save(postingan);
        return postingan;
    }

    @Override
    public List<PostinganTugasModel> retrieveListPostingan(){
        return postinganDB.findAll();
    }

    public PostinganTugasModel getPostinganByCode (Long kodePostingan){
        Optional<PostinganTugasModel> postingan = postinganDB.findById(kodePostingan);
        if (postingan.isPresent()){
            return postingan.get();
        }
        else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public PostinganTugasModel updatePostingan (Long kodePostingan, PostinganTugasModel postinganUpdate){
        PostinganTugasModel postingan = getPostinganByCode(kodePostingan);
        postingan.setJudulPostingan(postinganUpdate.getJudulPostingan());
        postingan.setMataPelajaran(postinganUpdate.getMataPelajaran());
        postingan.setTanggalDeadline(postinganUpdate.getTanggalDeadline());
        postingan.setDeskripsi(postinganUpdate.getDeskripsi());
        return postinganDB.save(postingan);
    }

    @Override
    public void deletePostingan (Long kodePostingan){
        PostinganTugasModel postingan = getPostinganByCode(kodePostingan);
//        if
        postinganDB.delete(postingan);
    }


}
