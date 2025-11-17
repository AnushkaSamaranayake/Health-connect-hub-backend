package com.hch.hch_backend.modules.users.repository;

import com.hch.hch_backend.modules.users.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
