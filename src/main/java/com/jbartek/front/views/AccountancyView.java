package com.jbartek.front.views;

import com.jbartek.front.MainLayout;
import com.jbartek.front.domain.Accountancy;
import com.jbartek.front.domain.FieldWork;
import com.jbartek.front.forms.AccountancyForm;
import com.jbartek.front.service.AccountancyService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route(value = "accountacy", layout = MainLayout.class)
@PageTitle("Accountacy | MyAgrii")
public class AccountancyView extends VerticalLayout {

    private Label accountancyLabel = new Label("ACCOUNTANCY");
    private VerticalLayout accountancyLayout = new VerticalLayout();
    private AccountancyForm accountancyForm = new AccountancyForm(this);
    private Grid<Accountancy> accountancyGrid = new Grid<>(Accountancy.class);
    private AccountancyService service = new AccountancyService();
    private Button addNewAccountancy = new Button("Add New");

    public AccountancyView() {
        accountancyLayout.add(accountancyLabel);
        accountancyLayout.setHorizontalComponentAlignment(Alignment.CENTER, accountancyLabel);
        accountancyGrid.setColumns("dateOfEvent", "typeOfEvent", "invoiceNumber", "product", "productQuantity",
                "netUnitPrice", "vatRate", "netTotalSum", "totalSum");
        addNewAccountancy.addClickListener(event -> {
            accountancyGrid.asSingleSelect().clear();
            accountancyForm.update.setVisible(false);
            accountancyForm.setAccountancy(new Accountancy());
            accountancyForm.save.setVisible(true);
            accountancyForm.userId.setValue("1");
            accountancyForm.userId.setVisible(false);
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewAccountancy);
        HorizontalLayout mainContent = new HorizontalLayout(accountancyGrid, accountancyForm);
        mainContent.setSizeFull();
        accountancyGrid.setSizeFull();
        add(toolbar, mainContent);
        setSizeFull();
        refresh();
        accountancyForm.setAccountancy(null);
        accountancyGrid.asSingleSelect().addValueChangeListener(event -> accountancyForm.setAccountancy(accountancyGrid.asSingleSelect().getValue()));
        accountancyGrid.addItemClickListener(event -> accountancyForm.update.setVisible(true));
        accountancyGrid.addItemClickListener(event -> accountancyForm.save.setVisible(false));
    }



    public void refresh(){
        service.fetchAll();
        accountancyGrid.setItems(service.getAccountancyList());
    }



}
