package com.equitativa;

import com.equitativa.pages.Home;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class ToDoListManagerApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return Home.class;
    }

}