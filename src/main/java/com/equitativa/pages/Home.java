package com.equitativa.pages;

import com.equitativa.pages.template.Template;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

public class Home extends Template {

    private static final long serialVersionUID = 1L;

    public Home() {
        List<String> persons = loadFeatures();
        ListDataProvider<String> listDataProvider = new ListDataProvider<>(persons);
        DataView<String> dataView = new DataView<String>("featuresRows", listDataProvider) {
            @Override
            protected void populateItem(Item<String> item) {
                String feature = item.getModelObject();
                RepeatingView repeatingView = new RepeatingView("feature");
                repeatingView.add(new Label(repeatingView.newChildId(), feature));
                item.add(repeatingView);
            }
        };
        add(dataView);
    }

    private List<String> loadFeatures() {
        List<String> features = new ArrayList<>();
        features.add("Creating Users, Projects & Tasks");
        features.add("Update tasks");
        features.add("Delete unwanted tasks");
        features.add("View project chart");
        return features;
    }
}
