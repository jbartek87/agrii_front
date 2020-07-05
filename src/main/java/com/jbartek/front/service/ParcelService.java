package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import com.jbartek.front.domain.Parcel;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class ParcelService {
    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

    private static ParcelService parcelService;
    private List<Parcel> parcelsList;

    public static ParcelService getInstance() {
        if (parcelService == null) {
            parcelService = new ParcelService();
        }
        return parcelService;
    }


    public Set getParcels() {
        return new HashSet<>(parcelsList);
    }

    public void fetchAll() {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint() + "parcelsBy/jbartek@jn.pl")
                .encode()
                .build()
                .toUri();
        Optional<Parcel[]> parcels = Optional.ofNullable(restTemplate.getForObject(url, Parcel[].class));
        parcelsList = new ArrayList<>(parcels
                .map(Arrays::asList)
                .orElse(new ArrayList<>()));
    }

    public List<Parcel> finByParcelNumber(String number){
        return parcelsList.stream()
                .filter(p->p.getParcelNumber().contains(number))
                .collect(Collectors.toList());
    }


    public void save(Parcel parcel){
        String url = appConfig.getBackendEndpoint() + "parcels";
        restTemplate.postForObject(url, (parcel), Void.class);
    }

    public void delete(long id) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint()+"parcels/" + id)
                .encode()
                .build()
                .toUri();
        restTemplate.delete(url);
    }
}
