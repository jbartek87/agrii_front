package com.jbartek.front.views;

import com.jbartek.front.MainLayout;
import com.jbartek.front.service.LorApiService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "news", layout = MainLayout.class)
@PageTitle("News | MyAgrii")
public class NewsView extends VerticalLayout {

    private LorApiService lorApiService = LorApiService.getInstance();
    private TextArea news = new TextArea("Breaking news");

    public NewsView(){
//        HorizontalLayout mainContent = new HorizontalLayout(news);
//        mainContent.setSizeFull();
        news.setSizeFull();
        add(news);
        setSizeFull();
        refresh();
    }

    public void refresh(){
        news.setValue(lorApiService.getNews());
    }
}
