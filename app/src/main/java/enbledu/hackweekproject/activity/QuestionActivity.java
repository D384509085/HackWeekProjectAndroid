package enbledu.hackweekproject.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.QuestionEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.adapter.QuestionRecycleViewAdapter;
import enbledu.hackweekproject.database.QuestionDAOImpl;
import enbledu.hackweekproject.helper.RecyclerItemClickListener;

public class QuestionActivity extends AppCompatActivity {

    private static final String TAG = "QuestionActivity";
    private Context mContext;
    private LayoutInflater inflater;
    private View mView;
    private RecyclerView mRecyclerView;
    private ArrayList<QuestionEntity> questionDatas;
    private QuestionRecycleViewAdapter myRecyclerVIewAdapter;
    private QuestionDAOImpl mDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.question_recyclerview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDatas();
        myRecyclerVIewAdapter = new QuestionRecycleViewAdapter(QuestionActivity.this, questionDatas);
        mRecyclerView.setAdapter(myRecyclerVIewAdapter);
        //设置recycleview的布局管理
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        refleshVIew();
    }

    private void refleshVIew() {
        questionDatas.clear();
        initDatas();
        myRecyclerVIewAdapter = new QuestionRecycleViewAdapter(mContext, questionDatas);
        mRecyclerView.setAdapter(myRecyclerVIewAdapter);
        //设置recycleview的布局管理
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        myRecyclerVIewAdapter.notifyDataSetChanged();
        myRecyclerVIewAdapter.setListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("note", questionDatas.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                Log.i(TAG,"OnItemLongClick");
                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.dialog_yes)
                        .setPositiveButton(R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        mDAO.deleteQuestion(questionDatas.get(position).getQuestionNumber());
                                        refleshVIew();

                                    }
                                }).setNegativeButton(R.string.no, null).create()
                        .show();
            }
        });
    }

    private void initDatas() {
        questionDatas = new ArrayList<QuestionEntity>();
        mDAO = new QuestionDAOImpl(mContext);
        questionDatas =  mDAO.getQuestion();
    }
}
