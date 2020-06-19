package io.gogz.qperdiem.models;

public class Rating {

    private String date;
    private Question question;

    public Rating(String date, Question question) {
        this.date = date;
        this.question = question;
    }

    public Rating() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
