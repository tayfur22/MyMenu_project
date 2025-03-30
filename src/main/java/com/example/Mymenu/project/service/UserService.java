package com.example.Mymenu.project.service;

import com.example.Mymenu.project.entity.User;

public interface UserService extends BaseService<User,Long>{

    User findByUsername(String username);

    User findByEmail(String email);
}
