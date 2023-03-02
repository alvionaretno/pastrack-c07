package com.PASTRACK.PASTRACK.Service.Role;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.RoleModel;
import com.PASTRACK.PASTRACK.Repository.RoleDB;




@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDB roleDb;

    @Override
    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }
    @Override
    public RoleModel getById(Long id) {
        Optional<RoleModel> role = roleDb.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }

    @Override
    public RoleModel getByName(String namaRole) {
        Optional<RoleModel> role = roleDb.findByRole(namaRole);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }
}
