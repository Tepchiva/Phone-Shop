package com.chiva.phoneshop.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "models")
public class Model {

    @Id
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE)  use global generate sequence
    @GeneratedValue(generator = "model_seq_generator")
    @SequenceGenerator(name = "model_seq_generator", initialValue = 1, sequenceName = "model_seq")
    private Integer id;

    //    @Column(name = "col_name")    use @column for customer column attribute
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "brand_code") we use @JoinColumn for custom col name relate model
    private Brand brand;

}
