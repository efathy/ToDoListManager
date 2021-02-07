package com.equitativa.pages.template;

import org.apache.wicket.markup.html.WebPage;

public abstract class Template extends WebPage {
    public Template() {
        super();
        add(new HeaderPanel("headerpanel"));
        add(new FooterPanel("footerpanel"));
    }
}
