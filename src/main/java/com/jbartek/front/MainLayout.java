package com.jbartek.front;

import com.jbartek.front.domain.Accountancy;
import com.jbartek.front.domain.PlantProtection;
import com.jbartek.front.domain.User;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("MyAgrii");
        logo.addClassName("logo");
        Anchor logout = new Anchor("logout", "==>Log out<==");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.addClassName("header");
        header.setWidth("100%");


        addToNavbar(header);
    }

    private void createDrawer(){
        RouterLink parcelLink = new RouterLink("Parcels", ParcelView.class);
        RouterLink fieldWorkLink = new RouterLink("Field Work", FieldWorkView.class);
        RouterLink plantProtectionLink = new RouterLink("Plant Protection", PlantProtectionView.class);
        RouterLink accountancyLink = new RouterLink("Accountancy", AccountancyView.class);
        RouterLink userLink = new RouterLink("Account Info", UserView.class);
        parcelLink.setHighlightCondition(HighlightConditions.sameLocation());
        fieldWorkLink.setHighlightCondition(HighlightConditions.sameLocation());
        plantProtectionLink.setHighlightCondition(HighlightConditions.sameLocation());
        accountancyLink.setHighlightCondition(HighlightConditions.sameLocation());
        userLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(parcelLink), new VerticalLayout( fieldWorkLink),
                new VerticalLayout(plantProtectionLink), new VerticalLayout(accountancyLink),
                new VerticalLayout(userLink)
        );
    }
}
