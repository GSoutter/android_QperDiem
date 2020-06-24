package io.gogz.qperdiem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.gogz.qperdiem.adapters.ContextQListAdapter;
import io.gogz.qperdiem.adapters.QuestionListAdapter;
import io.gogz.qperdiem.room_db.ContextQ;
import io.gogz.qperdiem.room_db.ContextWithQuestions;
import io.gogz.qperdiem.room_db.Question;
import io.gogz.qperdiem.viewmodels.ContextQViewModel;
import io.gogz.qperdiem.viewmodels.QuestionViewModel;

import static android.app.Activity.RESULT_OK;

public class FragmentContexts extends Fragment implements ContextQListAdapter.OnContextQListener{


    private static final String TAG = "FragmentContexts";
    private ContextQViewModel mContextQViewModel;
    public static final int NEW_CONTEXT_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_CONTEXT_ACTIVITY_REQUEST_CODE = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contexts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final ContextQListAdapter adapter = new ContextQListAdapter(getActivity().getBaseContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        mContextQViewModel = new ViewModelProvider(this).get(ContextQViewModel.class);

        mContextQViewModel.getAllContextWithQuestions().observe(getViewLifecycleOwner(), new Observer<List<ContextWithQuestions>>() {
            @Override
            public void onChanged(@Nullable final List<ContextWithQuestions> contextsWithQuestions) {
                // Update the cached copy of the questions in the adapter.
                adapter.setContextWithQs(contextsWithQuestions);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getBaseContext(), NewContextQActivity.class);
                startActivityForResult(intent, NEW_CONTEXT_ACTIVITY_REQUEST_CODE);
            }
        });



        return view;

    }

    //
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_CONTEXT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ContextQ contextQ = new ContextQ();
            contextQ.name = data.getStringExtra("contextQName");
            contextQ.contextId = data.getLongExtra("contextId", 0);
            if (data.getBooleanExtra("delete_toggle", false)){
                mContextQViewModel.deleteOne(contextQ);

            }else{
                mContextQViewModel.insert(contextQ);
            }
        }else if (requestCode == NEW_CONTEXT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ContextQ contextQ = new ContextQ();
            contextQ.name = data.getStringExtra(NewQuestionActivity.EXTRA_REPLY);
            mContextQViewModel.insert(contextQ);
        } else {
            Toast.makeText(
                    getActivity().getBaseContext(),
                    R.string.empty_not_saved_context,
                    Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContextQClick(int position) {
        Log.d(TAG, "onContextClick: clicked");

        Intent intent = new Intent(getActivity().getBaseContext(), EditContextQActivity.class);
        intent.putExtra("contextId", mContextQViewModel.getAllContextWithQuestions().getValue().get(position).context.contextId);
        startActivityForResult(intent, EDIT_CONTEXT_ACTIVITY_REQUEST_CODE);

    }
}