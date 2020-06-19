package io.gogz.qperdiem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.gogz.qperdiem.models.Context;
import io.gogz.qperdiem.models.Question;

import static org.junit.Assert.assertEquals;

public class ContextTest {

    private Context context;
    private Question question;
    private List<Question> questions;

    @Before
    public void before(){
        this.context = new Context("social", "people.png");
        this.question = new Question("did I do my best?", "metime");
        this.questions = new ArrayList<>();
        this.questions.add(question);
        this.questions.add(question);

    }


    @Test
    public void canGetName() {
        assertEquals("social", context.getName());

    }

    @Test
    public void canSetName() {
        context.setName("home");
        assertEquals("home", context.getName());

    }

    @Test
    public void canGetIcon() {
        assertEquals("people.png", context.getIcon());

    }

    @Test
    public void canSetIcon() {
        context.setName("pople.png");
        assertEquals("pople.png", context.getName());

    }

    @Test
    public void isActive() {
        assertEquals(true, context.isActive());

    }

    @Test
    public void canSetIActive() {
        context.setActive(false);
        assertEquals(false, context.isActive());
    }


    @Test
    public void hasEmptyQuestionsArray() {
        assertEquals(0, context.getQuestions().size());

      }

    @Test
    public void canSetQuestionsArray() {
        context.setQuestions(questions);
        assertEquals(2, context.getQuestions().size());

    }
    @Test
    public void canAddQuestions() {
        context.addQuestion(question);
        assertEquals(1, context.getQuestions().size());

    }

}
