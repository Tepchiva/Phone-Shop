package com.chiva.phoneshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE) use global generate sequence
    @GeneratedValue(generator = "brand_seq_generator")
    @SequenceGenerator(name = "brand_seq_generator", initialValue = 1, sequenceName = "brand_seq")
    private Integer id;

    @Column(unique = true)
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
