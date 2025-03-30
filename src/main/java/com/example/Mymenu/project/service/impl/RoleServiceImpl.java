package com.example.Mymenu.project.service.impl;

import com.example.Mymenu.project.entity.Role;
import com.example.Mymenu.project.Enum.RoleName;
import com.example.Mymenu.project.repository.RoleRepository;
import com.example.Mymenu.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }
}
