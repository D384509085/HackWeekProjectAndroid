package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.KeywordEntity;
import enbledu.hackweekproject.Entity.RobotEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.activity.EditActivity;
import enbledu.hackweekproject.database.KeywordDAOImpl;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class FragmentRobot extends Fragment {

    private KeywordDAOImpl mKeywordDao;
    private Context mContext;
    RobotEntity mRobotEntity;
    View mView;
    TextView qqText;
    TextView isSpeakAutoText;
    TextView keywourdText;
    ImageView robotImage;
    TextView robotNameText;
    CheckBox robotOpenChc;
    LinearLayout robotMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_robot, container, false);
        init();
        return mView;
    }

    private void init() {
        qqText = (TextView) mView.findViewById(R.id.qq_number);
        isSpeakAutoText = (TextView) mView.findViewById(R.id.robot_canspeak);
        keywourdText = (TextView) mView.findViewById(R.id.robot_keywourd);
        robotImage = (ImageView) mView.findViewById(R.id.robot_image);
        robotNameText = (TextView) mView.findViewById(R.id.robot_name);
        robotOpenChc = (CheckBox) mView.findViewById(R.id.robot_checkbox);
        robotMain = (LinearLayout) mView.findViewById(R.id.robot_main);
        mKeywordDao = new KeywordDAOImpl(mContext);

        robotMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("robot", mRobotEntity);
                startActivity(intent);
            }
        });
    }

    public FragmentRobot(Context context, RobotEntity robotEntity) {
        this.mContext = context;
        this.mRobotEntity = robotEntity;
    }

    @Override
    public void onResume() {
        initDatas();
        super.onResume();
    }

    private void initDatas() {
        Log.i("FragmentRobot", mRobotEntity.toString());
        robotOpenChc.setChecked(mRobotEntity.isOpen());
        robotNameText.setText(mRobotEntity.getName());
        qqText.setText(String.valueOf(mRobotEntity.getQqID()));
        switch (mRobotEntity.getCharacter()) {
            case 0:{
                robotImage.setImageResource(R.mipmap.small_wawa);
                break;
            }
            case 1:{
                robotImage.setImageResource(R.mipmap.small_mengmei);
                break;
            }
            case 2:{
                robotImage.setImageResource(R.mipmap.small_dashu);
                break;
            }
        }

        if (mRobotEntity.isSpeakAuto()) {
            isSpeakAutoText.setText("我能主动说话");
        } else {
            isSpeakAutoText.setText("我不能主动说话");
        }
        String keyword = "";
        ArrayList<KeywordEntity> list = mKeywordDao.getKeyWord();
        if (list!=null) {

            for (KeywordEntity keywordEntity : list) {
                keyword +=keywordEntity.getKeyWord()+"，";
            }
            keywourdText.setText(keyword);
        }
    }
}
