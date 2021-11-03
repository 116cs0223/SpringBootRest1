package com.example.demo.repository;

import com.example.demo.pojo.Alien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlienRepository extends CrudRepository<Alien, Integer> {
    List<Alien> findByAname(String ename);

    @Query("from Alien where aname=?1 order by aid desc")
    List<Alien> findByEnameSortByAid(String ename);

}
