package com.equitativa.pages.tasks;

import com.equitativa.pages.template.Template;
import com.equitativa.service.ProjectService;
import com.equitativa.service.TaskService;
import com.equitativa.service.UserService;
import com.equitativa.service.domain.Project;
import com.equitativa.service.domain.Task;
import com.equitativa.service.domain.TaskStatus;
import com.equitativa.service.domain.User;
import com.equitativa.service.impelmentation.ProjectServiceImpl;
import com.equitativa.service.impelmentation.TaskServiceImpl;
import com.equitativa.service.impelmentation.UserServiceImpl;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskSaver extends Template {

    private final TaskService taskService = new TaskServiceImpl();
    private final ProjectService projectService = new ProjectServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public TaskSaver(PageParameters pageParameters) {
        Task task = loadTask(pageParameters);
        Form<?> form = new Form<Void>("taskForm") {
            @Override
            protected void onSubmit() {
                if (task.getId() != null) {
                    taskService.updateTask(task.getId(), task);
                } else {
                    taskService.createTask(task);
                }
                setResponsePage(Tasks.class);
            }
        };
        addTaskNameField(task, form);
        addTaskDescriptionField(task, form);
        addProjectsDDL(task, form);
        addUsersFields(task, form);
        addStatusField(task, form);
        add(form);
    }

    private void addStatusField(Task task, Form<?> form) {
        ChoiceRenderer<TaskStatus> statusDropDownListRenderer = new ChoiceRenderer<>("value");
        final DropDownChoice<TaskStatus> status = new DropDownChoice<>(
                "status", new PropertyModel<>(task, "status"),
                Stream.of(TaskStatus.values()).collect(Collectors.toList()), statusDropDownListRenderer);


        form.add(status);
    }

    private void addUsersFields(Task task, Form<?> form) {
        List<User> users = userService.getUsers();
        ChoiceRenderer<User> userDropDownListRenderer = new ChoiceRenderer<>("name", "id");
        final DropDownChoice<User> reporter = new DropDownChoice<>(
                "reporter", new PropertyModel<>(task, "reporter"), users, userDropDownListRenderer);
        final DropDownChoice<User> assignedTo = new DropDownChoice<>(
                "assignedTo", new PropertyModel<>(task, "assignedTo"), users, userDropDownListRenderer);
        form.add(reporter);
        form.add(assignedTo);
    }

    private void addProjectsDDL(Task task, Form<?> form) {
        ChoiceRenderer<Project> projectDropDownListRenderer = new ChoiceRenderer<>("name", "id");
        final DropDownChoice<Project> projects = new DropDownChoice<>(
                "projects", new PropertyModel<>(task, "project"), projectService.getProjects(null),
                projectDropDownListRenderer);
        form.add(projects);
    }

    private void addTaskDescriptionField(Task task, Form<?> form) {
        final TextArea<String> taskDescription = new TextArea<>("description",
                new PropertyModel<>(task, "description"));
        form.add(taskDescription);
    }

    private void addTaskNameField(Task task, Form<?> form) {
        final TextField<String> taskName = new TextField<>("name", new PropertyModel<>(task, "name"));
        form.add(taskName);
    }

    private Task loadTask(PageParameters pageParameters) {
        if (pageParameters != null && pageParameters.getAllNamed() != null && pageParameters.getNamedKeys()
                                                                                            .contains("taskId")) {
            return taskService.findTask(Integer.parseInt(pageParameters.get("taskId").toString()));
        } else {
            return new Task();
        }
    }
}
