package com.PASTRACK.PASTRACK.Service.Postingan;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;



public interface PostinganService {

    PostinganTugasModel addPostingan(PostinganTugasModel postingan);
    List<PostinganTugasModel> retrieveListPostingan();
    PostinganTugasModel getPostinganByCode (Long kodePostingan);
    PostinganTugasModel updatePostingan (Long kodePostingan, PostinganTugasModel postinganUpdate);
    void deletePostingan (Long kodePostingan);

}