package com.chiva.phoneshop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
}
