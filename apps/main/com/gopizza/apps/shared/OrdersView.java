package com.gopizza.apps.shared;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.gopizza.ordering.order.application.search.OrderResponse;
import com.gopizza.ordering.order.application.search.OrderSearcher;
import com.gopizza.shared.domain.order.OrderPizza;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class OrdersView extends VerticalLayout {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String PIZZA_IMAGE = "/pizza.png";

    private final OrderSearcher searcher;
    private final FlexLayout layout = new FlexLayout();

    public OrdersView(OrderSearcher searcher) {
        this.searcher = searcher;

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        layout.setWidthFull();
        layout.setHeightFull();
        layout.getStyle()
            .set("display", "flex")
            .set("flex-wrap", "wrap")
            .set("gap", "20px")
            .set("align-items", "flex-start");

        add(layout);

        refresh();
        startAutoRefresh();
    }

    private void refresh() {
        layout.removeAll();
        var orders = OrderResponse.from(searcher.searchAll());
        orders.forEach(o -> layout.add(makeOrderCard(o)));
    }

    private Div makeOrderCard(OrderResponse o) {
        Div card = new Div();

        String bg = o.status().name().equals("COMPLETED") ? "#C8F7C5" : "white";

        card.getStyle()
            .set("width", "420px")
            .set("min-height", "320px")
            .set("border-radius", "16px")
            .set("padding", "20px")
            .set("background", bg)
            .set("box-shadow", "0 4px 14px rgba(0,0,0,0.08)")
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("gap", "12px");

        Span s1 = new Span("Order: " + o.id());
        s1.getStyle().set("font-weight", "700").set("font-size", "1.2rem");

        Span s2 = new Span("Status: " + o.status().name());
        Span s3 = new Span("Pizzas: " + o.pizzas().size());
        Span s4 = new Span("Total: $" + o.total());

        String created = o.createdAt()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .format(FORMAT);

        Span s5 = new Span(created);
        s5.getStyle().set("color", "#666").set("font-size", "0.85rem");

        FlexLayout pizzasRow = new FlexLayout();
        pizzasRow.setWidthFull();
        pizzasRow.getStyle()
            .set("display", "flex")
            .set("flex-wrap", "wrap")
            .set("gap", "12px");

        o.pizzas().forEach(p -> pizzasRow.add(makePizzaCard(p)));

        card.add(s1, s2, s3, s4, s5, pizzasRow);

        return card;
    }

    private Div makePizzaCard(OrderPizza p) {
        Div card = new Div();

        String bg = p.completed() ? "#A7F3D0" : "#E5E7EB";

        card.getStyle()
            .set("width", "70px")
            .set("padding", "8px")
            .set("border-radius", "10px")
            .set("background", bg)
            .set("display", "flex")
            .set("flex-direction", "column")
            .set("align-items", "center")
            .set("gap", "4px");

        Image img = new Image(PIZZA_IMAGE, "pizza");
        img.setWidth("100%");

        Span s2 = new Span(p.type().name());
        s2.getStyle().set("font-size", "0.7rem").set("text-align", "center");

        Span s3 = new Span(p.size().name());
        s3.getStyle().set("font-size", "0.7rem").set("color", "#555");

        card.add(img, s2, s3);

        return card;
    }

    private void startAutoRefresh() {
        UI ui = UI.getCurrent();
        ui.setPollInterval(1000);
        ui.addPollListener(e -> refresh());
    }
}
