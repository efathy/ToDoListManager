package com.equitativa.pages.template;

import com.equitativa.pages.Home;
import com.equitativa.pages.projects.Projects;
import com.equitativa.pages.tasks.Tasks;
import com.equitativa.pages.users.Users;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

    public HeaderPanel(String componentName) {
        super(componentName);

        add(new Link<Void>("homeNav") {
            @Override
            public void onClick() {
                setResponsePage(Home.class);
            }
        });

        add(new Link<Void>("createProjectNav") {
            @Override
            public void onClick() {
                setResponsePage(Projects.class);
            }
        });

        add(new Link<Void>("tasksNav") {
            @Override
            public void onClick() {
                setResponsePage(Tasks.class);
            }
        });

        add(new Link<Void>("usersNav") {
            @Override
            public void onClick() {
                setResponsePage(Users.class);
            }
        });
    }
}
