package com.counseling.userservice.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @org.hibernate.annotations.Type(type = "uuid-char")
//    private UUID id;
    private Integer id;

    private String name;

    private String username;

    private String password;

    @Column(name="is_enabled", columnDefinition = "boolean DEFAULT true")
    private boolean enable = true;

    @Column(name = "is_credentials_expired", columnDefinition = "boolean DEFAULT false")
    private boolean credentialsExpired;

    @Column(name = "is_locked", columnDefinition = "boolean DEFAULT false")
    private boolean locked = false;

    @Column(name = "is_expired", columnDefinition = "boolean DEFAULT false")
    private boolean expired = false;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd , HH:mm")
    private LocalDateTime createdDateTime;

    @OneToMany(mappedBy = "user")
    private List<UserRoleMapping> userRoleMappingList;
}
