package com.example.Mymenu.project.repository;

import com.example.Mymenu.project.entity.Role;
import com.example.Mymenu.project.Enum.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
