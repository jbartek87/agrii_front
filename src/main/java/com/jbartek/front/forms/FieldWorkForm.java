package com.jbartek.front.forms;

import com.jbartek.front.MainView;
import com.jbartek.front.domain.FieldWork;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.lang.reflect.Field;


public class FieldWorkForm extends FormLayout {
    public String[] workType = {"ORKA", "UPRAWA_BEZORKOWA", "UPRAWA_PRZEDSIEWNA", "SIEW", "NAWOZENIE", "ZBIOR"};

    private TextField dateOfWork = new TextField("Date of work");
    private TextField cultivatedPlant = new TextField("Cultivated plant");
    private ComboBox<String> fieldWorkType = new ComboBox<>("Type of work");
    private TextField comments = new TextField("Comments");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<FieldWork> binder = new Binder<>(FieldWork.class);

    private MainView mainView;

    public FieldWorkForm(MainView mainView){
        binder.bindInstanceFields(this);
        fieldWorkType.setItems(workType);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(dateOfWork, cultivatedPlant, fieldWorkType, comments, buttons);
    }

    public void save(){

    }

    public void delete(){

    }

    public void setFieldWork(FieldWork fieldWork) {
        binder.setBean(fieldWork);

        if (fieldWork==null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

}
