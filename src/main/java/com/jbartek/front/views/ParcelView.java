package com.jbartek.front.views;

import com.jbartek.front.MainLayout;
import com.jbartek.front.domain.Parcel;
import com.jbartek.front.forms.ParcelForm;
import com.jbartek.front.service.ParcelService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = MainLayout.class )
@PageTitle("Parcels | MyAgrii")
public class ParcelView extends VerticalLayout {

    private Label parcelLabel = new Label("PARCELS");
    private VerticalLayout parcelLayout = new VerticalLayout();
    private ParcelService parcelService = ParcelService.getInstance();
    private Grid<Parcel> parcelGrid = new Grid(Parcel.class);
    private ParcelForm parcelForm = new ParcelForm(this);
    private Button addNewParcel = new Button("Add new Parcel");

    public ParcelView() {
        parcelLayout.add(parcelLabel);
        parcelLayout.setHorizontalComponentAlignment(Alignment.CENTER, parcelLabel);
        parcelGrid.setColumns("parcelNumber", "precinct", "soilType", "area");
        addNewParcel.addClickListener(event -> {
            parcelGrid.asSingleSelect().clear();
            parcelForm.update.setVisible(false);
            parcelForm.setParcel(new Parcel());
            parcelForm.save.setVisible(true);
            parcelForm.userId.setValue(String.valueOf(parcelForm.getUserId()));
            parcelForm.userId.setVisible(false);
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewParcel);
        HorizontalLayout mainContent = new HorizontalLayout(parcelGrid, parcelForm);
        mainContent.setSizeFull();
        parcelGrid.setSizeFull();
        add(toolbar,mainContent);
        setSizeFull();
        refresh();
        parcelForm.setParcel(null);
        parcelGrid.asSingleSelect().addValueChangeListener(event -> parcelForm.setParcel(parcelGrid.asSingleSelect().getValue()));
        parcelGrid.addItemClickListener(event -> parcelForm.update.setVisible(true));
        parcelGrid.addItemClickListener(event -> parcelForm.save.setVisible(false));


    }

    public void refresh(){
        parcelService.fetchAll();
        parcelGrid.setItems(parcelService.getParcels());

    }

}
