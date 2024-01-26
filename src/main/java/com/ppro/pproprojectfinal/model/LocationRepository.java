package com.ppro.pproprojectfinal.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByid(Integer id);
}
