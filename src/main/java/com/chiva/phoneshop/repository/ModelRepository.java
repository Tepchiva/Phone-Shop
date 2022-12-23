package com.chiva.phoneshop.repository;

import com.chiva.phoneshop.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository /*optional*/
public interface ModelRepository extends JpaRepository<Model, Integer> {

}
