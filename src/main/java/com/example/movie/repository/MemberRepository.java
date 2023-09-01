package com.example.movie.repository;

import com.example.movie.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Member member) { // 회원가입
        em.persist(member);
        return member.getId();
    }
    
    public List<Member> findByEmail(String email) { // 가입시 중복 확인
        return em.createQuery(
                "select m from Member m where m.email = :email ",
                        Member.class)
                .setParameter("email", email)
                .getResultList();
    }
}
