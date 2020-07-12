package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import com.jbartek.front.domain.WeatherApi;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    public WeatherApi getWeatherDetails() {
        return api;
    }

    public void fetchWeather() {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint() + "api/weather/Krakow")
                .encode()
                .build()
                .toUri();
        api = restTemplate.getForObject(url, WeatherApi.class);
    }
    public String getWeather(String city){
        return restTemplate.getForObject(appConfig.getBackendEndpoint() + "api/weather/" + city, String.class);
    }
}
