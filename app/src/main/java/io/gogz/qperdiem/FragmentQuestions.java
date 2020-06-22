package io.gogz.qperdiem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.gogz.qperdiem.viewmodels.QuestionViewModel;

public class FragmentQuestions extends Fragment {


    private QuestionViewModel mQuestionViewModel;
    public static final int NEW_QUESTION_ACTIVITY_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questions, container, false);




    }
}
