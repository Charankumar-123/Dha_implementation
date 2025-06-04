package com.ac.dha.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Header")
@Getter
@Setter
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private String senderID;

    @Column(name = "receiver_id", nullable = false)
    private String receiverID;

    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;

    @Column(name = "record_count", nullable = false)
    private int recordCount;

    @Column(name = "disposition_flag", nullable = false)
    private String dispositionFlag;

    @OneToOne(mappedBy = "header", cascade = CascadeType.ALL)
    private Authorization authorization;
}