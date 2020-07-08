package com.jbartek.front.forms;



import com.jbartek.front.ParcelView;
import com.jbartek.front.domain.Parcel;
import com.jbartek.front.service.ParcelService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;



public class ParcelForm extends FormLayout {
    public String[] soilTypeValue = {"GRUNT_ORNY", "TUZ"};



    private TextField id = new TextField("Parcel ID");
    private TextField parcelNumber = new TextField("Parcel Number");
    private TextField precinct = new TextField("Precinct");
    private ComboBox<String> soilType = new ComboBox("Soil TYpe");
    private NumberField area = new NumberField("Area");
    private TextField userId = new TextField("User id");
    public com.vaadin.flow.component.button.Button update = new com.vaadin.flow.component.button.Button("Update");
    private Button delete = new Button("Delete");
    public Button save = new Button("Save");
    private Binder<Parcel> binder = new Binder<>(Parcel.class);
    private ParcelService service = ParcelService.getInstance();

    private ParcelView parcelView;

    public ParcelForm(ParcelView parcelView) {
        soilType.setItems(soilTypeValue);
        soilType.setAllowCustomValue(false);
        this.parcelView = parcelView;
        update.addClickListener(event -> update());
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        binder.bindInstanceFields(this);
        HorizontalLayout buttons = new HorizontalLayout(save, update,delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        id.setVisible(false);
        add(id,parcelNumber,precinct, soilType, area, userId, buttons);

    }

    public void update(){
        Parcel parcel = binder.getBean();
        service.upadate(parcel);
        parcelView.refresh();
        setParcel(null);
    }

    public void save(){
        Parcel parcel = binder.getBean();
        service.save(parcel);
        parcelView.refresh();
        setParcel(null);
    }

    public void delete(){
        Parcel parcel = binder.getBean();
        service.delete(Long.parseLong(parcel.getId()));
        parcelView.refresh();
        setParcel(null);
    }

    public void setParcel(Parcel parcel){
        binder.setBean(parcel);
        if(parcel==null){
            setVisible(false);
        } else {
            setVisible(true);
            parcelNumber.focus();
        }
    }

}
