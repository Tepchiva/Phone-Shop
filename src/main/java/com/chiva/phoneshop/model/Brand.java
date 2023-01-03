package com.chiva.phoneshop.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@ToString
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    //    @GeneratedValue(strategy = GenerationType.SEQUENCE) use global generate sequence
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotBlank
    @Column(unique = true)
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
