package com.jbartek.front.forms;

import com.jbartek.front.FieldWorkView;
import com.jbartek.front.domain.FieldWork;
import com.jbartek.front.service.FieldWorkService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class FieldWorkForm extends FormLayout {
    public String[] workType = {"ORKA", "UPRAWA_BEZORKOWA", "UPRAWA_PRZEDSIEWNA", "SIEW", "NAWOZENIE", "ZBIOR"};

    private DatePicker dateOfWork = new DatePicker("Date of work");
    private TextField cultivatedPlant = new TextField("Cultivated plant");
    private ComboBox<String> typeOfWork = new ComboBox<>("Type of work");
    private TextField comments = new TextField("Comments");
    private TextField parcelId = new TextField("Parcel Id");
    public com.vaadin.flow.component.button.Button update = new com.vaadin.flow.component.button.Button("Update");
    private Button delete = new Button("Delete");
    public Button save = new Button("Save");
    private Binder<FieldWork> binder = new Binder<>(FieldWork.class);
    private FieldWorkService service = FieldWorkService.getInstance();
    private FieldWorkView fieldWorkView;

    public FieldWorkForm(FieldWorkView fieldWorkView){
        typeOfWork.setItems(workType);
        typeOfWork.setAllowCustomValue(false);
        this.fieldWorkView = fieldWorkView;
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
        binder.bindInstanceFields(this);
        HorizontalLayout buttons = new HorizontalLayout(save, update,delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(dateOfWork, cultivatedPlant, typeOfWork, comments,parcelId,buttons);
    }

    public void update(){
        FieldWork fieldWork = binder.getBean();
        service.update(fieldWork);
        fieldWorkView.refresh();
        setFieldWork(null);
    }

    public void save(){
        FieldWork fieldWork = binder.getBean();
        service.save(fieldWork);
        fieldWorkView.refresh();
        setFieldWork(null);
    }

    public void delete(){
        FieldWork fieldWork = binder.getBean();
        service.delete(fieldWork.getId());
        fieldWorkView.refresh();
        setFieldWork(null);
    }

    public void setFieldWork(FieldWork fieldWork) {
        binder.setBean(fieldWork);
        if (fieldWork==null) {
            setVisible(false);
        } else {
            setVisible(true);
            cultivatedPlant.focus();
        }
    }

}
