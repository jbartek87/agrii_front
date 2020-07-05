package com.jbartek.front;

import com.jbartek.front.domain.FieldWork;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
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

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer(){
        RouterLink parcelLink = new RouterLink("Parcels", ParcelView.class);
        RouterLink fieldWorkLink = new RouterLink("Field Work", FieldWorkView.class);
        parcelLink.setHighlightCondition(HighlightConditions.sameLocation());
        fieldWorkLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(
                parcelLink, new VerticalLayout( fieldWorkLink)
        ));
    }
}
