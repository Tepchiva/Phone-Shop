package com.chiva.phoneshop.model.ro;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Getter
@Setter
@Table(name = "message_response_v")
public class MessageResponseRo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private Integer httpStatus;
    private String message;
}
