package enbledu.hackweekproject.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wx.wheelview.widget.WheelViewDialog;

import java.util.ArrayList;

import enbledu.hackweekproject.R;

public class NewRobotActivity extends AppCompatActivity implements View.OnClickListener {

    EditText robotNameEdi;
    EditText robotNumEdi;
    LinearLayout isSpeakAutoBtn;
    LinearLayout keywordBtn;
    LinearLayout questionBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_robot);
        init();
    }

    private void init() {
        robotNameEdi = (EditText) findViewById(R.id.new_robot_name);
        robotNameEdi.setOnClickListener(this);
        robotNumEdi = (EditText) findViewById(R.id.new_robot_people_num);
        robotNameEdi.setOnClickListener(this);
        isSpeakAutoBtn = (LinearLayout) findViewById(R.id.new_robot_is_speadk_btn);
        isSpeakAutoBtn.setOnClickListener(this);
        keywordBtn = (LinearLayout) findViewById(R.id.new_robot_keyword_btn);
        keywordBtn.setOnClickListener(this);
        questionBtn = (LinearLayout) findViewById(R.id.new_robot_question_);
        questionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_robot_is_speadk_btn: {
                showDialog(v);
                break;
            }

        }
    }

    public void showDialog(View view) {
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

    private ArrayList<String> createArraysforIsSpeak() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("是");
        list.add("否");
        return list;
    }
}
