package com.jbartek.front.forms;



import com.jbartek.front.MainView;
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



public class ParcelForm extends FormLayout {
    public String[] soilTypeValue = {"GRUNT_ORNY", "TUZ"};



    private TextField parcelNumber = new TextField("Parcel Number");
    private TextField precinct = new TextField("Precinct");
    private ComboBox<String> soilType = new ComboBox("Soil TYpe");
    private NumberField area = new NumberField("Area");
    private TextField userId = new TextField("User id");
    private com.vaadin.flow.component.button.Button save = new com.vaadin.flow.component.button.Button("Save");
    private Button delete = new Button("Delete");
    private Binder<Parcel> binder = new Binder<>(Parcel.class);
    private ParcelService service = ParcelService.getInstance();
    private Button addNewParcel = new Button("Xhamster");

    private MainView mainView;

    public ParcelForm(MainView mainView) {
        binder.bindInstanceFields(this);
        soilType.setItems(soilTypeValue);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addNewParcel.addClickListener(e->{
            setParcel(new Parcel());
        });
        add(parcelNumber,precinct, soilType, area, userId, buttons);

    }

    public void save(){
        Parcel parcel = binder.getBean();
        service.save(parcel);
        mainView.refresh();
//        setParcel(null);
    }

    public void delete(){
        Parcel parcel = binder.getBean();
        service.delete(parcel.getId());
        mainView.refresh();
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
