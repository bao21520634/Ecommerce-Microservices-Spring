package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String address1;
    private String address2;
    private String city;
    @Column(nullable = true)
    private String state;
    private String zipcode;
    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private Country country;
}