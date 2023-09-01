// 데이터: 고객등급(memberRating), 영화장르(genre), 영화관람등급(movieRating),
// 스크린(screen), 관람객(audience), 총좌석수(totalSeats), 결제수단(paymentMethod)
package com.example.movie.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Code {
    @Id
    @Column(name = "code_id")
    private String id; // 코드

    @Column(name = "code_name")
    private String name; // 이름
}