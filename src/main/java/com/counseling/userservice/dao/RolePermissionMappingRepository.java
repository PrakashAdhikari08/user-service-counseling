package com.counseling.userservice.dao;

import com.counseling.userservice.domain.RolePermissionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionMappingRepository extends JpaRepository<RolePermissionMapping,Integer> {
}
