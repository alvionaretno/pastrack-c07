package com.PASTRACK.PASTRACK.Service.Role;

import java.util.List;

import com.PASTRACK.PASTRACK.Model.RoleModel;

public interface RoleService {
    List<RoleModel> findAll();
    RoleModel getById(Long id);
    RoleModel getByName(String namaRole);
}
