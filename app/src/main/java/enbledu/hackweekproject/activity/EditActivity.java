package enbledu.hackweekproject.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wx.wheelview.widget.WheelViewDialog;

import java.io.IOException;
import java.util.ArrayList;

import enbledu.hackweekproject.Entity.RobotEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.database.RobotDAOImpl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EditActivity";
    Context mContext;
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
        setContentView(R.layout.activity_edit);
        mContext = EditActivity.this;
        ArrayList<RobotEntity> list = new ArrayList<RobotEntity>();
        list.add(new RobotEntity("robotname", 384509085, 0, 10, true, true));


        Gson gson = new Gson();
        String str = "";
        Log.i("EditActivity", str);
        for (RobotEntity keyWordEntity : list) {
            str += gson.toJson(keyWordEntity);
            str += "#";
        }
        MyAsyncTask to = new MyAsyncTask();
        to.execute(str);


        init();
    }

    private void init() {

        qqNumEdi = (EditText) findViewById(R.id.edit_robot_qqNum);
        robotNameEdi = (EditText) findViewById(R.id.edit_robot_name);
        robotNumEdi = (EditText) findViewById(R.id.edit_robot_people_num);
        isSpeakAutoBtn = (LinearLayout) findViewById(R.id.edit_robot_is_speadk_btn);
        isSpeakAutoBtn.setOnClickListener(this);
        keywordBtn = (LinearLayout) findViewById(R.id.edit_robot_keyword_btn);
        keywordBtn.setOnClickListener(this);
        questionBtn = (LinearLayout) findViewById(R.id.edit_robot_question_);
        questionBtn.setOnClickListener(this);
        ok_btn = (Button) findViewById(R.id.edit_robot_ok_btn);
        ok_btn.setOnClickListener(this);
        isSpeakAutoText = (TextView) findViewById(R.id.edit_is_speak_text);
        robotImage = (ImageView) findViewById(R.id.edit_robot_img);
        robotImage.setOnClickListener(this);
        Intent intent = getIntent();
        robotEntity = (RobotEntity) intent.getSerializableExtra("robot");

    }

    @Override
    protected void onResume() {
        super.onResume();
        robotNameEdi.setText(robotEntity.getName());
        qqNumEdi.setText(String.valueOf(robotEntity.getQqID()));
        switch (robotEntity.getCharacter()) {
            case 0:{
                robotImage.setImageResource(R.mipmap.big_wawa);
                break;
            }
            case 1:{
                robotImage.setImageResource(R.mipmap.big_mengmei);
                break;
            }
            case 2:{
                robotImage.setImageResource(R.mipmap.dashu);
                break;
            }
        }
        robotNumEdi.setText(String.valueOf(robotEntity.getStopSpeakingNum()));
        if (robotEntity.isSpeakAuto()) {
            isSpeakAutoText.setText("是");
        }
        else {
            isSpeakAutoText.setText("否");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_robot_is_speadk_btn: {
                showDialogForIsSpeak(v);
                break;
            }
            case R.id.edit_robot_keyword_btn: {
                Intent intent = new Intent(EditActivity.this, KeywordActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.edit_robot_question_: {
                Intent intent = new Intent(EditActivity.this, QuestionActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.edit_robot_ok_btn: {
                robotEntity.setName(robotNameEdi.getText().toString());
                robotEntity.setSpeakAuto(isSpeakAutoText.getText().toString()=="是");
                robotEntity.setStopSpeakingNum(Integer.parseInt(robotNumEdi.getText().toString()));
                robotEntity.setOpen(true);
                robotEntity.setQqID( Long.parseLong(qqNumEdi.getText().toString()));
                Log.i(TAG,robotEntity.toString());
                mDAO = new RobotDAOImpl(EditActivity.this);
                mDAO.updateRobot(robotEntity);


                finish();
                break;
            }
            case R.id.edit_robot_img: {
                showDialogForImage(v);
                break;
            }
        }
    }



    class MyAsyncTask extends AsyncTask<String, Void, Boolean> {


        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(mContext,"网络连接异常",Toast.LENGTH_SHORT).show();
            }
        }

        //真正耗时操作
        @Override
        protected Boolean doInBackground(String... params) {
            String str = params[0];
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/octet-stream");
            RequestBody body = RequestBody.create(mediaType, str);
            Request request = new Request.Builder()
                    .url("http://119.23.233.101:8080/xiaov-master/update?type=question")
                    .post(body)
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "1d894b75-b767-bb65-93c1-67ff05e7a9f7")
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                Log.i("EditActivity", str);
                Log.i("s", response.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(response != null && response.isSuccessful()) {
                return true;
            }
            else return false;

        }

    }
    public void showDialogForIsSpeak(View view) {
        WheelViewDialog dialog = new WheelViewDialog(this);
        dialog.setTitle("wheelview dialog").setItems(createArraysforIsSpeak()).setButtonText("确定").setDialogStyle(Color
                .parseColor("#6699ff")).setCount(5).show();
        dialog.setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
            @Override
            public void onItemClick(int position, String s) {
                TextView isSpeakAutoTex = (TextView) findViewById(R.id.edit_is_speak_text);
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
                        break;
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