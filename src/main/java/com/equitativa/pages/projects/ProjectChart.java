package com.equitativa.pages.projects;

import com.equitativa.pages.template.Template;
import com.equitativa.service.ProjectService;
import com.equitativa.service.domain.Project;
import com.equitativa.service.domain.Task;
import com.equitativa.service.domain.TaskStatus;
import com.equitativa.service.impelmentation.ProjectServiceImpl;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.googlecharts.*;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProjectChart extends Template {
    private ProjectService projectService = new ProjectServiceImpl();

    public ProjectChart(PageParameters pageParameters) {
        addChart(pageParameters);
    }

    private void addChart(PageParameters pageParameters) {
        Project project = projectService.findProject(Integer.parseInt(pageParameters.get("projectId").toString()));
        if (project != null && project.getTasksList() != null) {
            TreeMap<TaskStatus, Long> projectMap = project.getTasksList().stream().collect(
                    Collectors.groupingBy(Task::getStatus, TreeMap::new, Collectors.counting()));

            IChartData data = new AbstractChartData() {
                public double[][] getData() {
                    double[][] data = new double[1][projectMap.size()];
                    int i = 0;
                    for (Map.Entry<TaskStatus, Long> taskStatusLongEntry : projectMap.entrySet()) {
                        data[0][i++] = taskStatusLongEntry.getValue();
                    }
                    return data;
                }
            };
            ChartProvider provider = new ChartProvider(new Dimension(600, 400), ChartType.VENN, data);
            String[] taskStatus =
                    projectMap.keySet().stream().map(TaskStatus::getValue).collect(Collectors.toList())
                              .toArray(new String[0]);
            provider.setPieLabels(taskStatus);
            add(new Chart("projectChart", provider));
        }
    }
}
