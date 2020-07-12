package com.jbartek.front.views;

import com.jbartek.front.MainLayout;
import com.jbartek.front.service.WeatherApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "weather", layout = MainLayout.class )
@PageTitle("Weather| MyAgrii")
public class WeatherView extends VerticalLayout {

    public String[] cityList = {"Warszawa", "Poznan"};

    private WeatherApiService service = WeatherApiService.getInstance();
    private ComboBox<String> city = new ComboBox<>("Choose City");
    public Button confirmCity = new Button("OK");

    private WeatherApiService weatherService = new WeatherApiService().getInstance();
    private TextArea weatherStatus = new TextArea("Weather conditions:");

    public WeatherView(){
        city.setItems(cityList);
        city.setAllowCustomValue(false);
        confirmCity.addClickListener(event -> confirm());
        HorizontalLayout button = new HorizontalLayout(confirmCity);
        weatherStatus.setSizeFull();
        add(city,confirmCity,weatherStatus);
        setSizeFull();
    }

    public void confirm(){
       weatherStatus.setValue(service.getWeather(city.getValue()));
    }
}
