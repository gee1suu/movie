package com.example.movie.dto;

import com.example.movie.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoForm {
    private String name;
    private String email;
    private Role role;
}
