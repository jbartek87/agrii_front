package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import com.jbartek.front.domain.WeatherApi;
import org.springframework.web.client.RestTemplate;

public class WeatherApiService {


    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

    private static WeatherApiService service;
    private WeatherApi api;

    public static WeatherApiService getInstance() {
        if (service == null) {
            service = new WeatherApiService();
        }
        return service;
    }

    public String getWeather(String city){
        return restTemplate.getForObject(appConfig.getBackendEndpoint() + "api/weather/" + city, String.class);
    }
}
