package com.equitativa;

import com.equitativa.pages.Home;
import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class ToDoListManagerApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();
        Bootstrap.install(this);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }

}