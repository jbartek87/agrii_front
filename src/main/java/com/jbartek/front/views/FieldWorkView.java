package com.jbartek.front.views;

import com.jbartek.front.MainLayout;
import com.jbartek.front.domain.FieldWork;
import com.jbartek.front.domain.Parcel;
import com.jbartek.front.forms.FieldWorkForm;
import com.jbartek.front.service.FieldWorkService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
    private FieldWorkForm fieldWorkForm = new FieldWorkForm(this);
    private Button addNewFieldWork = new Button("Add New FieldWork");


    public FieldWorkView() {
        fieldWorkLayout.add(fieldWorkLabel);
        fieldWorkLayout.setHorizontalComponentAlignment(Alignment.CENTER, fieldWorkLabel);
        fieldWorkGrid.setColumns("dateOfWork", "cultivatedPlant", "typeOfWork", "comments");
        HorizontalLayout mainContent = new HorizontalLayout(fieldWorkGrid, fieldWorkForm);
        HorizontalLayout toolbar = new HorizontalLayout(addNewFieldWork);
        addNewFieldWork.addClickListener(event -> {
          fieldWorkGrid.asSingleSelect().clear();
            fieldWorkForm.update.setVisible(false);
            fieldWorkForm.setFieldWork(new FieldWork());
            fieldWorkForm.save.setVisible(true);
        });
        mainContent.setSizeFull();
        fieldWorkGrid.setSizeFull();
        add(toolbar,mainContent);
        setSizeFull();
        refresh();
        fieldWorkForm.setFieldWork(null);
        fieldWorkGrid.asSingleSelect().addValueChangeListener(event -> fieldWorkForm.setFieldWork(fieldWorkGrid.asSingleSelect().getValue()));
        fieldWorkGrid.addItemClickListener(event -> fieldWorkForm.update.setVisible(true));
        fieldWorkGrid.addItemClickListener(event -> fieldWorkForm.save.setVisible(false));
    }

    public void refresh() {
        fieldWorkService.fetchAll();
        fieldWorkGrid.setItems(fieldWorkService.getFieldWorkList());
    }




}

