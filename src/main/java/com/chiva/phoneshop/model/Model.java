package com.chiva.phoneshop.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Setter
@Getter
@ToString
@Entity
@Table(name = "models")
public class Model {

    // @GeneratedValue(strategy = GenerationType.SEQUENCE)  use global generate sequence
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    //    @Column(name = "col_name")    use @column for customer column attribute
    @Column(nullable = false)
    private String name;

    @ManyToOne
    // @JoinColumn(name = "brand_code") we use @JoinColumn for custom col name relate model
    private Brand brand;
}
