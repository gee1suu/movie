package com.example.movie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@SequenceGenerator(
        name = "seq_ticket_id",
        sequenceName = "seq_ticket_id",
        allocationSize = 1)
@Getter
public class Ticket {
    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_ticket_id")
    @Column(name = "ticket_id")
    private Long id; // 코드

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member memberId; // 예매자

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
    private Schedule scheduleId; // 스케줄코드

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "price_id")
    private Price priceId; // 가격

    @ManyToOne
    @JoinColumn(name = "payment_method", referencedColumnName = "code_id")
    private Code paymentMethod; // 결제수단

    private Timestamp ticketDate; // 예매날짜
    private int seatRow; // 좌석행
    private int seatCol; // 좌석열

}
