package io.gogz.qperdiem;

import org.junit.Before;
import org.junit.Test;

import io.gogz.qperdiem.models.Question;
import io.gogz.qperdiem.models.Rating;

import static org.junit.Assert.assertEquals;

public class RatingTest {

    private Question question;
    private Question question2;
    private Rating rating;

    @Before
    public void before(){
        this.question = new Question("did I do my best?", "metime");
        this.question2 = new Question("I do my best?", "metime");
        this.rating = new Rating("2002-06-06", question);
    }

    @Test
    public void canGetDate() {
        assertEquals("2002-06-06", rating.getDate());
    }
    @Test
    public void canSetDate() {
        rating.setDate("2020-06-06");
        assertEquals("2020-06-06", rating.getDate());
    }

    @Test
    public void canGetQuestion() {
        assertEquals("did I do my best?", rating.getQuestion().getText());
    }
    @Test
    public void cansetQuestion() {

        rating.setQuestion(question2);
        assertEquals("I do my best?", rating.getQuestion().getText());
    }
}
