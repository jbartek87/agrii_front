package com.jbartek.front;

import com.jbartek.front.domain.Accountancy;
import com.jbartek.front.forms.AccountancyForm;
import com.jbartek.front.service.AccountancyService;
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

    public AccountancyView() {
        accountancyLayout.add(accountancyLabel);
        accountancyLayout.setHorizontalComponentAlignment(Alignment.CENTER, accountancyLabel);
        accountancyGrid.setColumns("dateOfEvent", "typeOfEvent", "invoiceNumber", "product", "productQuantity",
                "netUnitPrice", "vatRate", "netTotalSum", "totalSum");
        HorizontalLayout mainContent = new HorizontalLayout(accountancyGrid, accountancyForm);
        mainContent.setSizeFull();
        accountancyGrid.setSizeFull();
        add(mainContent);
        setSizeFull();
        refresh();
        accountancyForm.setAccountancy(null);
        accountancyGrid.asSingleSelect().addValueChangeListener(event -> accountancyForm.setAccountancy(accountancyGrid.asSingleSelect().getValue()));
    }



    public void refresh(){
        service.fetchAll();
        accountancyGrid.setItems(service.getAccountancyList());
    }



}
