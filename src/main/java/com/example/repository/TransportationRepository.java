package com.example.repository;


import com.example.model.Location;
import com.example.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransportationRepository extends JpaRepository<Transportation, Long> {
    List<Transportation> findByFrom(Location from);

    List<Transportation> findByTo(Location from);

    //@Query("SELECT t FROM Transportation t WHERE t.from IN :fromList AND t.to IN :toList")
    //List<Transportation> findByFromInAndToIn(@Param("fromList") List<Location> fromList, @Param("toList") List<Location> toList);

    List<Transportation> findByFromAndTo(Location from, Location to);
}
