package com.jbartek.front.views;

import com.jbartek.front.MainLayout;
import com.jbartek.front.domain.User;
import com.jbartek.front.forms.UserForm;
import com.jbartek.front.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "user", layout = MainLayout.class)
@PageTitle("User Settings | MyAgrii")
public class UserView extends VerticalLayout{
    private Label userLabel = new Label("Settings");
    private VerticalLayout userLayout = new VerticalLayout();
    private UserService userService = UserService.getInstance();
    private Grid<User> userGrid = new Grid<>(User.class);

    public UserView() {
        refresh();
        initUserSectionControls();
        add(userLayout, userGrid);
    }

    public void refresh(){
        userService.fetchUser();
        userGrid.setItems(userService.getUserDetails());
    }

    public void initUserSectionControls(){
        userLayout.add(userLabel);
        userLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, userLabel);

        //FILTER

        userGrid.setColumns("firstName", "lastName", "farmNumber", "email", "password");
    }
}
