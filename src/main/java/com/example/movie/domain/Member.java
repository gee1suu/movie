package com.example.movie.domain;

import com.example.movie.dto.MemberForm;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@DynamicInsert
@SequenceGenerator(
        name = "seq_member_id",
        sequenceName = "seq_member_id",
        allocationSize = 1)
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_member_id")
    @Column(name = "member_id")
    private Long id; // 코드(기본키)

    @Column(name = "member_name")
    private String name; // 이름

    @Enumerated(EnumType.STRING)
    @Column(name = "member_rating")
    private Role role; // 인증등급

    @Column(unique = true)
    private String email; // 이메일

    private String password; // 비밀번호

    public static Member createUser(MemberForm memberForm, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setName(memberForm.getName());
        member.setEmail(memberForm.getEmail());
        member.setRole(Role.GEN);

        String password = passwordEncoder.encode(memberForm.getPassword());
        member.setPassword(password);

        return member;
    }
}