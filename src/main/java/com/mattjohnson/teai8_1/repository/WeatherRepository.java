package com.mattjohnson.teai8_1.repository;

import com.mattjohnson.teai8_1.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherModel, Long> {
}
