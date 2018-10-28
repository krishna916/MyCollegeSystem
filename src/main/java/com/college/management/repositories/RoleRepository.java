package com.college.management.repositories;

import com.college.management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String role);
}
