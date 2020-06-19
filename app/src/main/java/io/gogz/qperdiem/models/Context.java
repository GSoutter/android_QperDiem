package io.gogz.qperdiem.models;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private String name;
    private String icon;
    private List<Question> questions;
    private boolean active;

    public Context(String name, String icon) {
        this.name = name;
        this.icon = icon;
        this.questions = new ArrayList<>();
        this.active = true;
    }

    public Context() {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
