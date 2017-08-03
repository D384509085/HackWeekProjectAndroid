package enbledu.hackweekproject.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wx.wheelview.widget.WheelViewDialog;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.RobotEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.database.RobotDAOImpl;

public class NewRobotActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "NewRobotActivity";
    private RobotEntity robotEntity;
    EditText qqNumEdi;
    EditText robotNameEdi;
    EditText robotNumEdi;
    LinearLayout isSpeakAutoBtn;
    LinearLayout keywordBtn;
    LinearLayout questionBtn;
    TextView isSpeakAutoText;
    Button ok_btn;
    ImageView robotImage;
    private RobotDAOImpl mDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_robot);
        init();
    }

    private void init() {
        qqNumEdi = (EditText) findViewById(R.id.new_robot_qqNum);
        robotNameEdi = (EditText) findViewById(R.id.new_robot_name);
        robotNumEdi = (EditText) findViewById(R.id.new_robot_people_num);
        isSpeakAutoBtn = (LinearLayout) findViewById(R.id.new_robot_is_speadk_btn);
        isSpeakAutoBtn.setOnClickListener(this);
        keywordBtn = (LinearLayout) findViewById(R.id.new_robot_keyword_btn);
        keywordBtn.setOnClickListener(this);
        questionBtn = (LinearLayout) findViewById(R.id.new_robot_question_);
        questionBtn.setOnClickListener(this);
        ok_btn = (Button) findViewById(R.id.new_robot_ok_btn);
        ok_btn.setOnClickListener(this);
        isSpeakAutoText = (TextView) findViewById(R.id.is_speak_text);
        robotImage = (ImageView) findViewById(R.id.robit_image);
        robotImage.setOnClickListener(this);
        robotEntity = new RobotEntity();
        robotEntity.setCharacter(2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_robot_is_speadk_btn: {
                showDialogForIsSpeak(v);
                break;
            }
            case R.id.new_robot_keyword_btn: {
                Intent intent = new Intent(NewRobotActivity.this, KeywordActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.new_robot_question_: {
                Intent intent = new Intent(NewRobotActivity.this, QuestionActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.new_robot_ok_btn: {
                robotEntity.setName(robotNameEdi.getText().toString());
                robotEntity.setSpeakAuto(isSpeakAutoText.getText().toString()=="是");
                robotEntity.setStopSpeakingNum(Integer.parseInt(robotNumEdi.getText().toString()));
                robotEntity.setOpen(true);
                robotEntity.setQqID( Long.parseLong(qqNumEdi.getText().toString()));
                Log.i(TAG,robotEntity.toString());
                mDAO = new RobotDAOImpl(NewRobotActivity.this);
                mDAO.insertRobot(robotEntity);
                finish();
                break;
            }
            case R.id.robit_image: {
                showDialogForImage(v);
                break;
            }
        }
    }

    public void showDialogForIsSpeak(View view) {
        WheelViewDialog dialog = new WheelViewDialog(this);
        dialog.setTitle("wheelview dialog").setItems(createArraysforIsSpeak()).setButtonText("确定").setDialogStyle(Color
                .parseColor("#6699ff")).setCount(5).show();
        dialog.setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
            @Override
            public void onItemClick(int position, String s) {
                TextView isSpeakAutoTex = (TextView) findViewById(R.id.is_speak_text);
                isSpeakAutoTex.setText(s);
            }
        });
    }

    public void showDialogForImage(View view) {
        WheelViewDialog dialog = new WheelViewDialog(this);
        dialog.setTitle("wheelview dialog").setItems(createArraysforImage()).setButtonText("确定").setDialogStyle(Color
                .parseColor("#6699ff")).setCount(5).show();
        dialog.setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
            @Override
            public void onItemClick(int position, String s) {
                switch (s) {
                    case "大叔":{
                        robotImage.setImageResource(R.mipmap.big_dashu);
                        robotEntity.setCharacter(2);
                        break;
                    }
                    case "萌妹":{
                        robotImage.setImageResource(R.mipmap.big_mengmei);
                        robotEntity.setCharacter(1);
                        break;
                    }
                    case "小孩":{
                        robotImage.setImageResource(R.mipmap.big_wawa);
                        robotEntity.setCharacter(0);
                    }
                }
            }
        });
    }
    private ArrayList<String> createArraysforIsSpeak() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("是");
        list.add("否");
        return list;
    }
    private ArrayList<String> createArraysforImage() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("大叔");
        list.add("萌妹");
        list.add("小孩");
        return list;
    }
}
