package com.example.projecttraining.models;

public class SwitchModel {
    private int id;
    private String name;
    private String icon;
    private String link;
    private String requireDesktop;
    private String category;
    private boolean isChecked = false;

    public SwitchModel() {
    }

    public SwitchModel(String name, String icon, String link, String requireDesktop, String category) {
        this.name = name;
        this.icon = icon;
        this.link = link;
        this.requireDesktop = requireDesktop;
        this.category = category;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRequireDesktop() {
        return requireDesktop;
    }

    public void setRequireDesktop(String requireDesktop) {
        this.requireDesktop = requireDesktop;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
