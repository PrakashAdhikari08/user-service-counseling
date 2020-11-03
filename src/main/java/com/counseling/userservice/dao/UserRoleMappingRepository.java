package com.counseling.userservice.dao;

import com.counseling.userservice.domain.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Integer> {
}
