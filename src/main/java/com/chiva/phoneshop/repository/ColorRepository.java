package com.chiva.phoneshop.repository;

import com.chiva.phoneshop.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
}
