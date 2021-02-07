package com.equitativa.pages.projects;

import com.equitativa.pages.template.Template;
import com.equitativa.service.ProjectService;
import com.equitativa.service.domain.Project;
import com.equitativa.service.impelmentation.ProjectServiceImpl;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class Projects extends Template {

    private ProjectService projectService = new ProjectServiceImpl();

    public Projects() {
        addProjectsTable();
        addCreateProjectButton();
    }

    private void addCreateProjectButton() {
        add(new Link<Void>("createProject") {
            @Override
            public void onClick() {
                setResponsePage(ProjectsCreation.class);
            }
        });
    }

    private void addProjectsTable() {
        List<Project> projects = projectService.getProjects(null);
        ListDataProvider<Project> listDataProvider = new ListDataProvider<>(projects);
        DataView<Project> dataView = new DataView<Project>("projectRows", listDataProvider) {
            @Override
            protected void populateItem(Item<Project> item) {
                Project project = item.getModelObject();
                RepeatingView repeatingView = new RepeatingView("project");
                repeatingView.add(new Label(repeatingView.newChildId(), project.getName()));
                repeatingView.add(new Label(repeatingView.newChildId(), project.getManager().getName()));
                item.add(repeatingView);

                Link<Void> viewChartLink = new Link<Void>("chart") {
                    @Override
                    public void onClick() {
                        PageParameters pageParameters = new PageParameters();
                        pageParameters.set("projectId", project.getId());
                        setResponsePage(ProjectChart.class, pageParameters);
                    }
                };
                item.add(viewChartLink);
            }
        };
        add(dataView);
    }
}
