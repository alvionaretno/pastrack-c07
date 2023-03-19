package com.PASTRACK.PASTRACK.Repository;

import java.util.Optional;

import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostinganDB extends JpaRepository<PostinganTugasModel, Long> {

    Optional<PostinganTugasModel> findById(Long kodePostingan);
}
