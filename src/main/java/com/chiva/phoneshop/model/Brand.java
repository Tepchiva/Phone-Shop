package com.chiva.phoneshop.model;

import com.chiva.phoneshop.utils.Constant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank
    @Column(nullable = false, columnDefinition = "varchar(3) default 'ACT'")
    private String status = Constant.STATUS_ACT;

    public Brand(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
