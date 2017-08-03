package enbledu.hackweekproject.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.QuestionEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.adapter.QuestionRecycleViewAdapter;
import enbledu.hackweekproject.database.QuestionDAOImpl;

public class QuestionActivity extends AppCompatActivity {

    private Context mContext;
    private LayoutInflater inflater;
    private View mView;
    private RecyclerView mRecyclerView;
    private ArrayList<QuestionEntity> noteDatas;
    private QuestionRecycleViewAdapter myRecyclerVIewAdapter;
    private QuestionDAOImpl mDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();
    }

    private void initView() {
    }
}
