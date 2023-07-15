package de.dreierschach.dsablatt.heldendokument.components;

import org.springframework.web.context.annotation.SessionScope;

@SessionScope
abstract public class Presenter<V extends View> {
    protected V view;

    abstract public void bind();

    public void setView(V view) {
        this.view = view;
    }
}
