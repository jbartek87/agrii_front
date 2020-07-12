package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import org.springframework.web.client.RestTemplate;

public class LorApiService {
    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

    private static LorApiService service;

    public static LorApiService getInstance() {
        if (service == null) {
            service = new LorApiService();
        }
        return service;
    }

    public String getNews(){
        return restTemplate.getForObject(appConfig.getBackendEndpoint() + "news", String.class);
    }
}
