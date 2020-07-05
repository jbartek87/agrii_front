package com.jbartek.front.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppConfig {

    private static AppConfig appConfig;

    public static AppConfig getInstance(){
        if(appConfig==null){
            appConfig = new AppConfig();
        }
        return appConfig;
    }

    private String backendEndpoint = "http://localhost:8080/v1/";
}
