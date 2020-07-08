package com.jbartek.front;

import com.jbartek.front.domain.PlantProtection;
import com.jbartek.front.forms.PlantProtectionForm;
import com.jbartek.front.service.PlantProtectionService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "plantProtection", layout = MainLayout.class)
@PageTitle("Plant Protection | MyAgrii")
public class PlantProtectionView extends VerticalLayout {

    private Label plantProtectionLabel = new Label("PLANT PROTECTION");
    private VerticalLayout plantProtectionLayout = new VerticalLayout();
    private PlantProtectionService plantProtectionService = PlantProtectionService.getInstance();
    private Grid<PlantProtection> plantProtectionGrid = new Grid<>(PlantProtection.class);
    private PlantProtectionForm plantProtectionForm = new PlantProtectionForm(this);


    public PlantProtectionView() {
        plantProtectionLayout.add(plantProtectionLabel);
        plantProtectionLayout.setHorizontalComponentAlignment(Alignment.CENTER, plantProtectionLabel);
        plantProtectionGrid.setColumns("cultivatedPlant","dateOfWork","dose","productName","protectionType");
        HorizontalLayout mainContent = new HorizontalLayout(plantProtectionGrid, plantProtectionForm);
        mainContent.setSizeFull();
        plantProtectionGrid.setSizeFull();
        add(mainContent);
        setSizeFull();
        refresh();
        plantProtectionForm.setPlantProtection(null);
        plantProtectionGrid.asSingleSelect().addValueChangeListener(event -> plantProtectionForm.setPlantProtection(plantProtectionGrid.asSingleSelect().getValue()));

    }

    public void refresh() {
        plantProtectionService.fetchAll();
        plantProtectionGrid.setItems(plantProtectionService.getPlantProtectionList());
    }




}

