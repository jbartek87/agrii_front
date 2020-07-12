package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import com.jbartek.front.domain.FieldWork;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public class FieldWorkService {
    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

    private static FieldWorkService fieldWorkService;
    private List<FieldWork> fieldWorkList;

    public static FieldWorkService getInstance(){
        if(fieldWorkService == null){
            fieldWorkService = new FieldWorkService();
        }
        return fieldWorkService;
    }

    public Set getFieldWorkList(){

        return new HashSet<>(fieldWorkList);
    }

    public void fetchAll() {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint() + "fieldWorkByUser/farmer@wp.pl")
                .encode()
                .build()
                .toUri();
        Optional<FieldWork[]> fieldWorks = Optional.ofNullable(restTemplate.getForObject(url, FieldWork[].class));
        fieldWorkList = new ArrayList<>(fieldWorks
                .map(Arrays::asList)
                .orElse(new ArrayList<>()));
    }


    public void update(FieldWork fieldWork){
        String url = appConfig.getBackendEndpoint() + "fieldWork";
        restTemplate.put(url, (fieldWork), Void.class);
    }

    public void save(FieldWork fieldWork){
        String url = appConfig.getBackendEndpoint() + "fieldWork";
        restTemplate.postForObject(url, (fieldWork), Void.class);
    }

    public void delete(long id){
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint()+ "fieldWork/" +id)
                .encode()
                .build()
                .toUri();
        restTemplate.delete(url);
    }



}
