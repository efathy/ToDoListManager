package com.equitativa;

import com.equitativa.dataaccess.utils.ConnectionHandler;
import com.equitativa.pages.Tasks;
import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class ToDoListManagerApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();
        ConnectionHandler.initialize();
        Bootstrap.install(this);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return Tasks.class;
    }

}