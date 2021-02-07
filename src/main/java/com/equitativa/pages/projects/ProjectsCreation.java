package com.equitativa.pages.projects;

import com.equitativa.pages.template.Template;
import com.equitativa.service.ProjectService;
import com.equitativa.service.UserService;
import com.equitativa.service.domain.Project;
import com.equitativa.service.domain.User;
import com.equitativa.service.impelmentation.ProjectServiceImpl;
import com.equitativa.service.impelmentation.UserServiceImpl;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class ProjectsCreation extends Template {

    private ProjectService projectService = new ProjectServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public ProjectsCreation(PageParameters parameters) {
        Project project = new Project();

        final TextField<String> projectName = new TextField<String>("name", new PropertyModel<>(project, "name"));
        List<User> users = userService.getUsers();

        ChoiceRenderer<User> userDropDownListRenderer = new ChoiceRenderer<>("name", "id");
        final DropDownChoice<User> manager = new DropDownChoice<>(
                "manager", new PropertyModel<>(project, "manager"), users, userDropDownListRenderer);

        Form<?> form = new Form<Void>("projectForm") {
            @Override
            protected void onSubmit() {
                projectService.createProject(project);
                setResponsePage(Projects.class);
            }
        };

        form.add(projectName);
        form.add(manager);
        add(form);
    }
}
