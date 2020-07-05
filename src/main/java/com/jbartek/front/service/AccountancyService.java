package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import org.springframework.web.client.RestTemplate;

public class AccountancyService {
    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

}
