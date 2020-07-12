package com.jbartek.front.service;

import com.jbartek.front.config.AppConfig;
import com.jbartek.front.domain.Parcel;
import com.jbartek.front.domain.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class UserService {
    private RestTemplate restTemplate = new RestTemplate();
    private AppConfig appConfig = AppConfig.getInstance();

    private static UserService userService;
    private User user;

    public static UserService getInstance(){
        if(userService == null){
            userService = new UserService();
        }
        return  userService;
    }

    public User getUserDetails() {
        return user;
    }

    public User fetchUser(){
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getBackendEndpoint() + "usersByEmail/farmer@wp.pl")
                .encode()
                .build()
                .toUri();
        user = restTemplate.getForObject(url, User.class);
        return user;
    }

    public void save(User user){
        String url = appConfig.getBackendEndpoint() + "users";
        restTemplate.postForObject(url, (user), Void.class);
    }

    public Boolean validate(){
        String url = appConfig.getBackendEndpoint() + "validateUser/farmer@wp.pl&12345";
       return restTemplate.getForObject(url, Boolean.class);
    }

}
