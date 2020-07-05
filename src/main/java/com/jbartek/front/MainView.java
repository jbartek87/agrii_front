package com.jbartek.front;

import com.jbartek.front.domain.FieldWork;
import com.jbartek.front.domain.Parcel;
import com.jbartek.front.forms.FieldWorkForm;
import com.jbartek.front.forms.ParcelForm;
import com.jbartek.front.service.FieldWorkService;
import com.jbartek.front.service.ParcelService;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route
public class MainView extends VerticalLayout {

    private Label parcelLabel = new Label();
    private Label fieldWorkLabel = new Label();



    private ParcelService parcelService = ParcelService.getInstance();
    private FieldWorkService fieldWorkService = FieldWorkService.getInstance();


    private Grid<Parcel> parcelGrid = new Grid(Parcel.class);
    private Grid<FieldWork> fieldWorkGrid = new Grid<>(FieldWork.class);


    private ParcelForm parcelForm = new ParcelForm(this);
    private FieldWorkForm fieldWorkForm = new FieldWorkForm(this);


    private TextField filter = new TextField();



    public MainView() {
        createHeader();
        filter.setPlaceholder("Filter by parcel number");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e->update());
        parcelGrid.setColumns("parcelNumber", "precinct", "soilType", "area");
        HorizontalLayout mainContent = new HorizontalLayout(parcelGrid, parcelForm);
        mainContent.setSizeFull();
        parcelGrid.setSizeFull();
        add(filter, mainContent);
        setSizeFull();
        refresh();

    }


    public void update(){
        parcelGrid.setItems(parcelService.finByParcelNumber(filter.getValue()));
    }

    public void refresh(){
        parcelService.fetchAll();
        parcelGrid.setItems(parcelService.getParcels());
        fieldWorkService.fetchAll();
        fieldWorkGrid.setItems(fieldWorkService.getFieldWorkList());
    }

    private void createHeader() {
        H1 logo = new H1("MyAgrii");
        logo.addClassName("logo");

        Anchor logout = new Anchor("logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");
        add(header);
    }

    private void hideForms(){
        parcelForm.setParcel(null);
        fieldWorkForm.setFieldWork(null);
    }
}
