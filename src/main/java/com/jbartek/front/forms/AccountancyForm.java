package com.jbartek.front.forms;

import com.jbartek.front.views.AccountancyView;
import com.jbartek.front.domain.Accountancy;
import com.jbartek.front.service.AccountancyService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class AccountancyForm extends FormLayout {
    public String[] eventType = {"INCOME", "EXPENDITURE"};

    private TextField id = new TextField("ID");
    private DatePicker dateOfEvent = new DatePicker("Date");
    private ComboBox<String> typeOfEvent = new ComboBox<>("Type of event");
    private TextField invoiceNumber = new TextField("Invoice number");
    private TextField product = new TextField("Product Name");
    private TextField productQuantity = new TextField("Quantity");
    private TextField netUnitPrice = new TextField("Netto Price");
    private TextField vatRate = new TextField("VAT");
    private TextField netTotalSum = new TextField("Neto total sum");
    private TextField totalSum = new TextField("Total sum");
    public TextField userId = new TextField("User id");
    public com.vaadin.flow.component.button.Button update = new com.vaadin.flow.component.button.Button("Update");
    private Button delete = new Button("Delete");
    public Button save = new Button("Save");
    private AccountancyService service = AccountancyService.getInstance();
    private Binder<Accountancy> binder = new Binder<>(Accountancy.class);
    private AccountancyView accountancyView;


    public AccountancyForm(AccountancyView accountancyView){
        typeOfEvent.setItems(eventType);
        typeOfEvent.setAllowCustomValue(false);
        this.accountancyView = accountancyView;
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        update.addClickListener(event -> update());
        id.setVisible(false);
        binder.bindInstanceFields(this);
        HorizontalLayout buttons = new HorizontalLayout(save, update,delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(id,dateOfEvent,typeOfEvent,invoiceNumber,product,productQuantity,netUnitPrice,vatRate,netTotalSum,
                totalSum,userId,buttons);
    }

    public void update(){
        Accountancy accountancy = binder.getBean();
        service.update(accountancy);
        accountancyView.refresh();
        setAccountancy(null);
    }

    public void save(){
        Accountancy accountancy = binder.getBean();
        service.save(accountancy);
        accountancyView.refresh();
        setAccountancy(null);
    }

    public void delete(){
        Accountancy accountancy = binder.getBean();
        service.delete(Long.parseLong(accountancy.getId()));
        accountancyView.refresh();
        setAccountancy(null);
    }

    public void setAccountancy(Accountancy accountancy){
        binder.setBean(accountancy);
        if(accountancy==null){
            setVisible(false);
        }else {
            setVisible(true);
            invoiceNumber.focus();
        }
    }
}
