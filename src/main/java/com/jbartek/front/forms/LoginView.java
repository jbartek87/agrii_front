package com.jbartek.front.forms;

import com.jbartek.front.domain.User;
import com.jbartek.front.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Vaadin CRM")

public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    UserService service = UserService.getInstance();

    private LoginForm login = new LoginForm();
    private Button register = new Button("REGISTER");

    public LoginView(){
        register.addClickListener(validate -> checkout());
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(new H1("Vaadin CRM"), login, register);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    private void createUser(){
        User user = new User();
        user.setId(1);
        user.setEmail("farmer@wp.pl");
        user.setFirstName("Karol");
        user.setLastName("Marshal");
        user.setFarmNumber("PL2000");
        user.setPassword("12345");
        service.save(user);
    }

    public void checkout(){
        if(service.validate()){
            createUser();
            Notification.show("User has been created");
        }else Notification.show("Users already exists");
    }



}
