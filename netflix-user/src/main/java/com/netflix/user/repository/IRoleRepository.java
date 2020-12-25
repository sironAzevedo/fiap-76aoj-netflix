package com.netflix.user.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.user.model.Role;
import com.netflix.user.model.enums.PerfilEnum;

public interface IRoleRepository  extends JpaRepository<Role, Long> {

	Set<Role> findByName(PerfilEnum name);
}
