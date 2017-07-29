package enbledu.hackweekproject.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wx.wheelview.widget.WheelViewDialog;

import java.util.ArrayList;
import java.util.List;

import enbledu.hackweekproject.Entity.RobotEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.adapter.MyFragmentAdapter;
import enbledu.hackweekproject.database.RobotDAOImpl;
import enbledu.hackweekproject.fragment.FragmentAdd;
import enbledu.hackweekproject.fragment.FragmentRobot;
import enbledu.hackweekproject.helper.DepthPageTransformer;

public class MainActivity extends AppCompatActivity {

    private RobotDAOImpl mRobotDAO;
    private ViewPager mvViewPager;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initViewPage();
    }
    public void showDialog(View view) {
        WheelViewDialog dialog = new WheelViewDialog(this);
        dialog.setTitle("wheelview dialog").setItems(createArrays()).setButtonText("确定").setDialogStyle(Color
                .parseColor("#6699ff")).setCount(5).show();
    }
    private ArrayList<String> createArrays() {
        ArrayList<String> list = new ArrayList <String>();
        for (int i = 0; i < 20; i++) {
            list.add("item" + i);
        }
        return list;
    }
    private void initViewPage() {

        mRobotDAO = new RobotDAOImpl(mContext);
        ArrayList<RobotEntity> robotDatas;
        robotDatas = mRobotDAO.getRobotDatas();
        List<Fragment> list = new ArrayList<Fragment>();
        for (RobotEntity robotEntity : robotDatas) {
            FragmentRobot fragmentRobot = new FragmentRobot(mContext,robotEntity);
            list.add(fragmentRobot);
        }
        FragmentAdd fragmentAdd = new FragmentAdd(mContext);


        list.add(fragmentAdd);

        /*list.add(fragmentTodoList);
        list.add(fragment2);*/
        /*mvViewPager = (ViewPager) findViewById(R.id.pager);*/
        mvViewPager = (ViewPager) findViewById(R.id.vp);
        mvViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        mvViewPager.setPageTransformer(true, new DepthPageTransformer());
        mvViewPager.setCurrentItem(0);
        mvViewPager.setOffscreenPageLimit(5);
    }
}
