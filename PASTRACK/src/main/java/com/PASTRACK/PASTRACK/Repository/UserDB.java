package com.PASTRACK.PASTRACK.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PASTRACK.PASTRACK.Model.UserModel;


@Repository
public interface UserDB extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    UserModel findById(String Id);
}
