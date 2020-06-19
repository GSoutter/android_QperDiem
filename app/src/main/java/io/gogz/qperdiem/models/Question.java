package io.gogz.qperdiem.models;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String text;
    private List<Rating> ratings;
    private List<Context> contexts;
    private boolean active;
    private String icon;

    public Question(String text, String icon) {
        this.text = text;
        this.active = true;
        this.icon = icon;
        this.ratings = new ArrayList<>();
        this.contexts = new ArrayList<>();
    }

    public Question() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void addContext(Context context){
        this.contexts.add(context);
    }
}
