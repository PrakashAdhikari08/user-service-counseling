package com.counseling.userservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<UserRoleMapping> userRoleMappingList;

    @OneToMany(mappedBy = "role")
    private List<RolePermissionMapping> rolePermissionMappingList;
}
