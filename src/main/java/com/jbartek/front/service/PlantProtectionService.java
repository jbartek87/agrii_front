package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import com.jbartek.front.domain.PlantProtection;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public class PlantProtectionService {
    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

    private static PlantProtectionService plantProtectionService;
    private List<PlantProtection> plantProtectionList;


    public static PlantProtectionService getInstance(){
        if(plantProtectionService==null){
            plantProtectionService = new PlantProtectionService();
        }
        return plantProtectionService;
    }

    public Set getPlantProtectionList(){
        return new HashSet<>(plantProtectionList);
    }

    public void fetchAll() {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint() + "plantProtectionEmail/kodilla@test.pl")
                .encode()
                .build()
                .toUri();
        Optional<PlantProtection[]> plantProtections = Optional.ofNullable(restTemplate.getForObject(url, PlantProtection[].class));
        plantProtectionList = new ArrayList<>(plantProtections
                .map(Arrays::asList)
                .orElse(new ArrayList<>()));
    }

    public void save(PlantProtection plantProtection){
        String url = appConfig.getBackendEndpoint() + "plantProtection";
        restTemplate.put(url, (plantProtection), Void.class);
    }

    public void delete(long id){
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint()+ "plantProtection/" + id)
                .encode()
                .build()
                .toUri();
        restTemplate.delete(url);
    }
}
