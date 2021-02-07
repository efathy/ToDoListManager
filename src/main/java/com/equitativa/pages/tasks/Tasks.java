package com.equitativa.pages.tasks;

import com.equitativa.pages.projects.ProjectChart;
import com.equitativa.pages.template.Template;
import com.equitativa.service.TaskService;
import com.equitativa.service.domain.Task;
import com.equitativa.service.impelmentation.TaskServiceImpl;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class Tasks extends Template {

    private final TaskService taskService = new TaskServiceImpl();

    public Tasks() {
        List<Task> tasks = taskService.getTasks(null, null);
        ListDataProvider<Task> listDataProvider = new ListDataProvider<>(tasks);
        DataView<Task> dataView = new DataView<Task>("taskRows", listDataProvider) {
            @Override
            protected void populateItem(Item<Task> item) {
                Task task = item.getModelObject();
                RepeatingView repeatingView = new RepeatingView("task");
                repeatingView.add(new Label(repeatingView.newChildId(), task.getProject().getName()));
                repeatingView.add(new Label(repeatingView.newChildId(), task.getName()));
                repeatingView.add(new Label(repeatingView.newChildId(), task.getAssignedTo().getName()));
                repeatingView.add(new Label(repeatingView.newChildId(), task.getStatus().getValue()));
                item.add(repeatingView);

                Link<Void> updateBtn = new Link<Void>("editBtn") {
                    @Override
                    public void onClick() {
                        PageParameters pageParameters = new PageParameters();
                        pageParameters.set("taskId", task.getId());
                        setResponsePage(TaskSaver.class, pageParameters);
                    }
                };
                item.add(updateBtn);

                Link<Void> deleteBtn = new Link<Void>("deleteBtn") {
                    @Override
                    public void onClick() {
                        taskService.deleteTask(task.getId());
                        setResponsePage(Tasks.class);
                    }
                };
                item.add(deleteBtn);
            }
        };

        add(new Link<Void>("createTask") {
            @Override
            public void onClick() {
                setResponsePage(TaskSaver.class);
            }
        });
        add(dataView);
    }


}
