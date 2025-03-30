package com.example.Mymenu.project.service;

import com.example.Mymenu.project.entity.Role;
import com.example.Mymenu.project.Enum.RoleName;

public interface RoleService {
    Role findByName(RoleName name);
}
