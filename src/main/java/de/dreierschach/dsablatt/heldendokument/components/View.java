package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class View<P extends Presenter> extends HorizontalLayout {

    private Component content;
    private Component emptyContent;

    @Autowired
    private P presenter;

    public Component getContent() {
        return content;
    }

    public abstract Component createContent();

    public Presenter getPresenter() {
        return presenter;
    }

    public Component createEmptyContent() {
        var content = new VerticalLayout();
        content.setWidth(100, Unit.PERCENTAGE);
        content.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        content.add(new NativeLabel("Keine Daten vorhanden."));
        return content;
    }

    public void setEmpty(boolean empty) {
        content.setVisible(!empty);
        emptyContent.setVisible(empty);
    }

    public View() {
        super();
        content = createContent();
        emptyContent = createEmptyContent();
        setSizeFull();
        add(new NativeLabel("MENU"));
        addAndExpand(content, emptyContent);
        setEmpty(true);
    }

    @PostConstruct
    protected void init() {
        presenter.setView(this);
        presenter.bind();
    }
}
