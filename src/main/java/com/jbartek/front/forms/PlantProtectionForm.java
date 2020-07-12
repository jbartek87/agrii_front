package com.jbartek.front.forms;


import com.jbartek.front.service.ParcelService;
import com.jbartek.front.views.PlantProtectionView;
import com.jbartek.front.domain.PlantProtection;
import com.jbartek.front.service.PlantProtectionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;
import java.util.stream.Collectors;


public class PlantProtectionForm extends FormLayout {
    private ParcelService parcelService = ParcelService.getInstance();
    public List<String> fieldNumber = parcelService.fetchAll().stream()
            .map(x -> x.getId())
            .collect(Collectors.toList());

    public String[] protectionTypeValue = {"FUNGICYDE", "INSECTICIDE", "HERBICIDE"};
    private TextField id = new TextField("ID");
    private DatePicker dateOfWork = new DatePicker("Date of protection");
    private TextField productName = new TextField("Plant protection product name");
    private ComboBox<String> protectionType = new ComboBox<>("Protection Type");
    private NumberField dose = new NumberField("Dosage of Product");
    private TextField cultivatedPlant = new TextField("Plant Name");
    private ComboBox<String> parcelId = new ComboBox<>("Parcel Id");
    public com.vaadin.flow.component.button.Button update = new com.vaadin.flow.component.button.Button("Update");
    private Button delete = new Button("Delete");
    public Button save = new Button("Save");
    private Binder<PlantProtection> binder = new Binder<>(PlantProtection.class);
    private PlantProtectionService service = PlantProtectionService.getInstance();

    private PlantProtectionView plantProtectionView;

    public PlantProtectionForm(PlantProtectionView plantProtectionView) {
        protectionType.setItems(protectionTypeValue);
        protectionType.setAllowCustomValue(false);
        parcelId.setItems(fieldNumber);
        parcelId.setAllowCustomValue(false);
        this.plantProtectionView = plantProtectionView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        update.addClickListener(event -> update());
        binder.bindInstanceFields(this);
        HorizontalLayout buttons = new HorizontalLayout(save, update,delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        id.setVisible(false);
        add(id,dateOfWork,productName, protectionType, dose, cultivatedPlant, parcelId, buttons);

    }

    public void update() {
        PlantProtection plantProtection = binder.getBean();
        service.save(plantProtection);
        plantProtectionView.refresh();
        setPlantProtection(null);
    }

    public void save() {
        PlantProtection plantProtection = binder.getBean();
        service.save(plantProtection);
        plantProtectionView.refresh();
        setPlantProtection(null);
    }

    public void delete() {
        PlantProtection plantProtection = binder.getBean();
        service.delete(Long.parseLong(plantProtection.getId()));
        plantProtectionView.refresh();
        setPlantProtection(null);

    }

    public void setPlantProtection(PlantProtection plantProtection) {
        binder.setBean(plantProtection);
        if (plantProtection == null) {
            setVisible(false);
        } else {
            setVisible(true);
            productName.focus();
        }
    }


}
