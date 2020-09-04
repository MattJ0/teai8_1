package com.mattjohnson.teai8_1.service.client;

import com.mattjohnson.teai8_1.model.WeatherModel;
import com.mattjohnson.teai8_1.repository.WeatherRepository;
import com.mattjohnson.teai8_1.service.pojo.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class WeatherService {

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${apiKey}")
    private String apiKey;

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    private CurrentWeather getCurrentWeather() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CurrentWeather> currentWeatherResponseEntity = restTemplate.exchange(baseUrl + apiKey,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                CurrentWeather.class);

        return currentWeatherResponseEntity.getBody();
    }

    private WeatherModel mapCurrentWeatherOnModel(CurrentWeather currentWeather) {
        WeatherModel weatherModel = new WeatherModel();

        weatherModel.setName(currentWeather.getName());
        weatherModel.setDescription(Collections.singletonList(currentWeather.getWeather()).get(0).get(0).getDescription());
        weatherModel.setTemperature(currentWeather.getMain().getTemp());
        weatherModel.setPressure(currentWeather.getMain().getPressure());
        weatherModel.setHumidity(currentWeather.getMain().getHumidity());
        weatherModel.setWindSpeed(currentWeather.getWind().getSpeed());
        weatherModel.setClouds(currentWeather.getClouds().getAll());

        return weatherModel;
    }


    @Scheduled(cron = "0 * * ? * *")
    private void saveCurrentWeatherInRepo() {
        weatherRepository.save(mapCurrentWeatherOnModel(getCurrentWeather()));
    }

    //TODO add logger


}
