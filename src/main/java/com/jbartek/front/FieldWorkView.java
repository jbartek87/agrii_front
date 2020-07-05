package com.jbartek.front;

import com.jbartek.front.domain.FieldWork;
import com.jbartek.front.forms.FieldWorkForm;
import com.jbartek.front.service.FieldWorkService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "fieldWork", layout = MainLayout.class)
@PageTitle("FieldWork | MyAgrii")
public class FieldWorkView extends VerticalLayout {


    private Label fieldWorkLabel = new Label("FIELD WORK");
    private VerticalLayout fieldWorkLayout = new VerticalLayout();
    private FieldWorkService fieldWorkService = FieldWorkService.getInstance();
    private Grid<FieldWork> fieldWorkGrid = new Grid<>(FieldWork.class);
    private FieldWorkForm fieldWorkForm = new FieldWorkForm();


    public FieldWorkView() {
        refresh();
        initParcelSectionControls();
        add(fieldWorkLayout, fieldWorkForm, fieldWorkGrid);

    }

    public void refresh() {
        fieldWorkService.fetchAll();
        fieldWorkGrid.setItems(fieldWorkService.getFieldWorkList());
    }


    private void initParcelSectionControls() {
        fieldWorkLayout.add(fieldWorkLabel);
        fieldWorkLayout.setHorizontalComponentAlignment(Alignment.CENTER, fieldWorkLabel);

        // HERE PUT FILTER

        fieldWorkGrid.setColumns("dateOfWork", "cultivatedPlant", "typeOfWork", "comments");
    }


}

