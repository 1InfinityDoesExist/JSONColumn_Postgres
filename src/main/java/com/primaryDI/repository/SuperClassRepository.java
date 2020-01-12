package com.primaryDI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.primaryDI.Beans.SuperClass;

public interface SuperClassRepository extends JpaRepository<SuperClass, Long>, CrudRepository<SuperClass, Long> {

}
