package com.chiva.phoneshop.repository.ro;

import com.chiva.phoneshop.exception.ErrorResponse;
import com.chiva.phoneshop.model.ro.MessageResponseRo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MessageResponseRepository extends JpaRepository<MessageResponseRo, Integer> {
    Optional<MessageResponseRo> findByCode(String code);
}
