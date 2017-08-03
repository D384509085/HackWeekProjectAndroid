package enbledu.hackweekproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

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
        Intent intent = new Intent(mContext,NewRobotActivity.class);
        startActivity(intent);
    }



    private void initViewPage() {

        mRobotDAO = new RobotDAOImpl(mContext);
        ArrayList<RobotEntity> robotDatas;
        robotDatas = mRobotDAO.getRobotDatas();
        List<Fragment> list = new ArrayList<Fragment>();
        for (RobotEntity robotEntity : robotDatas) {
            FragmentRobot fragmentRobot = new FragmentRobot(mContext, robotEntity);
            list.add(fragmentRobot);
        }
        FragmentAdd fragmentAdd = new FragmentAdd(mContext);
        FragmentRobot fragmentRobot = new FragmentRobot(mContext, new RobotEntity());

        list.add(fragmentAdd);
        list.add(fragmentRobot);
        /*list.add(fragmentTodoList);
        list.add(fragment2);*/
        /*mvViewPager = (ViewPager) findViewById(R.id.pager);*/
        mvViewPager = (ViewPager) findViewById(R.id.vp);
        mvViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
        mvViewPager.setPageTransformer(true, new DepthPageTransformer());
        mvViewPager.setCurrentItem(0);
        mvViewPager.setOffscreenPageLimit(5);
        mvViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        ImageView img = (ImageView) findViewById(R.id.page1);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 1: {
                        ImageView img = (ImageView) findViewById(R.id.page2);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 2: {
                        ImageView img = (ImageView) findViewById(R.id.page3);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 3: {
                        ImageView img = (ImageView) findViewById(R.id.page4);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 4: {
                        ImageView img = (ImageView) findViewById(R.id.page5);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ImageView img = (ImageView) findViewById(R.id.page1);
        img.setImageResource(R.mipmap.large_circle);
    }
}
