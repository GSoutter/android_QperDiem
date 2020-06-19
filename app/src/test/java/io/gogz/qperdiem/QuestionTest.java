package io.gogz.qperdiem;

import org.junit.Before;
import org.junit.Test;

import io.gogz.qperdiem.models.Question;

import static org.junit.Assert.assertEquals;

public class QuestionTest {

    private Question question;

    @Before
    public void before(){
        this.question = new Question("did I do my best?", "metime");
    }

    @Test
    public void canGetText() {
        assertEquals("did I do my best?", question.getText());
    }

    @Test
    public void canSetText() {
        question.setText("nope");
        assertEquals("nope", question.getText());
    }

    @Test
    public void canGetActive() {
        assertEquals(true, question.isActive());
    }

    @Test
    public void canSetActive() {
        question.setActive(false);
        assertEquals(false, question.isActive());
    }

    @Test
    public void canGetIcon() {
        assertEquals("metime", question.getIcon());
    }

    @Test
    public void canSetIcon() {
        question.setIcon("bulb");
        assertEquals("bulb", question.getIcon());
    }

    @Test
    public void hasEmptyRatingsArray() {
        assertEquals(0, question.getRatings().size());
    }
    @Test
    public void hasEmptyContextsArray() {
        assertEquals(0, question.getContexts().size());
    }
}
