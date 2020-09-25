package com.counseling.userservice.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Enumerated
    private Role role;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd , HH:mm")
    private LocalDateTime createdDateTime;
}
