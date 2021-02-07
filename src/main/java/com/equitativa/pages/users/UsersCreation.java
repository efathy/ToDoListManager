package com.equitativa.pages.users;

import com.equitativa.pages.template.Template;
import com.equitativa.service.UserService;
import com.equitativa.service.domain.User;
import com.equitativa.service.impelmentation.UserServiceImpl;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class UsersCreation extends Template {

    private UserService userService = new UserServiceImpl();

    public UsersCreation() {
        User user = new User();

        final TextField<String> userName = new TextField<String>("name", new PropertyModel<>(user, "name"));

        Form<?> form = new Form<Void>("userForm") {
            @Override
            protected void onSubmit() {
                userService.createUser(user);
                setResponsePage(Users.class);
            }
        };

        form.add(userName);
        add(form);
    }
}
