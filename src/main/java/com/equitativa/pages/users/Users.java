package com.equitativa.pages.users;

import com.equitativa.pages.template.Template;
import com.equitativa.service.UserService;
import com.equitativa.service.domain.User;
import com.equitativa.service.impelmentation.UserServiceImpl;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.util.List;

public class Users extends Template {

    private UserService userService = new UserServiceImpl();

    public Users() {
        List<User> users = userService.getUsers();
        ListDataProvider<User> listDataProvider = new ListDataProvider<>(users);
        DataView<User> dataView = new DataView<User>("userRows", listDataProvider) {
            @Override
            protected void populateItem(Item<User> item) {
                User feature = item.getModelObject();
                RepeatingView repeatingView = new RepeatingView("user");
                repeatingView.add(new Label(repeatingView.newChildId(), feature.getName()));
                item.add(repeatingView);
            }
        };

        add(new Link<Void>("createUser") {
            @Override
            public void onClick() {
                setResponsePage(UsersCreation.class);
            }
        });
        add(dataView);
    }
}
