package com.manufacture.expertservice.model;

//import com.manufacture.identityservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String name;
  //  private Set<Role> roles;
    private String password;
    private String email;
}
