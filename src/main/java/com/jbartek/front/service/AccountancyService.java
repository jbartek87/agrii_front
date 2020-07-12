package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import com.jbartek.front.domain.Accountancy;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public class AccountancyService {
    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

    private static AccountancyService service;
    private List<Accountancy> accountancyList;

    public static AccountancyService getInstance(){
        if(service == null){
            service = new AccountancyService();
        }
        return service;
    }

    public Set getAccountancyList(){
        return new HashSet(accountancyList);
    }

    public void fetchAll(){
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint() + "accountancyByEmail/farmer@wp.pl")
                .encode()
                .build()
                .toUri();
        Optional<Accountancy[]> accountancies = Optional.ofNullable(restTemplate.getForObject(url, Accountancy[].class));
        accountancyList = new ArrayList<>(accountancies
                .map(Arrays::asList)
                .orElse(new ArrayList<>()));
    }

    public void save(Accountancy accountancy){
        String url = appConfig.getBackendEndpoint() + "accountancy";
        restTemplate.postForObject(url, (accountancy), Void.class);
    }

    public void update(Accountancy accountancy){
        String url = appConfig.getBackendEndpoint() + "accountancy";
        restTemplate.put(url, (accountancy), Void.class);
    }

    public void delete(long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint()+ "accountancy/"+ id)
                .encode()
                .build()
                .toUri();
        restTemplate.delete(url);
    }
}
