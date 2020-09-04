package com.mattjohnson.teai8_1.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "weather_records", schema = "teai8_1")
@Getter
@Setter
public class WeatherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double temperature;

    private Integer pressure;

    private Integer humidity;

    @Column(name = "wind_speed")
    private Double windSpeed;

    private Integer clouds;

    public WeatherModel(String name, String description, Double temperature, Integer pressure,
                        Integer humidity, Double windSpeed, Integer clouds) {
        this.name = name;
        this.description = description;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.clouds = clouds;
    }

    public WeatherModel() {

    }
}
