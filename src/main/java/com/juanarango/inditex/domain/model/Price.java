package com.juanarango.inditex.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRICES")
public class Price implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private long priceList;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    private int priority;

    private double price;

    private String currency;
}
