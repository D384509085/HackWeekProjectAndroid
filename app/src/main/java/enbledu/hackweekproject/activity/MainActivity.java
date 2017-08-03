package enbledu.hackweekproject.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

       /* Intent intent = new Intent(mContext,NewRobotActivity.class);
        startActivity(intent);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewPage();
    }

    private void initViewPage() {

        mRobotDAO = new RobotDAOImpl(mContext);
        ArrayList<RobotEntity> robotDatas;
        robotDatas = mRobotDAO.getRobotDatas();
        List<Fragment> list = new ArrayList<Fragment>();
        for (RobotEntity robotEntity : robotDatas) {
            FragmentRobot fragmentRobot = new FragmentRobot(mContext, robotEntity);
            Log.i("MainActivity",robotEntity.toString());
            list.add(fragmentRobot);
        }
        final FragmentAdd fragmentAdd = new FragmentAdd(mContext);
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
                ArrayList<ImageView> imgs = new ArrayList<ImageView>();
                ImageView img0 = (ImageView) findViewById(R.id.page1);
                ImageView img1 = (ImageView) findViewById(R.id.page2);
                ImageView img2 = (ImageView) findViewById(R.id.page3);
                ImageView img3 = (ImageView) findViewById(R.id.page4);
                ImageView img4 = (ImageView) findViewById(R.id.page5);
                imgs.add(img0);
                imgs.add(img1);
                imgs.add(img2);
                imgs.add(img3);
                imgs.add(img4);
                switch (position) {
                    case 0: {
                        for (ImageView imageView : imgs) {
                            imageView.setImageResource(R.mipmap.small_circle);
                        }
                        ImageView img = (ImageView) findViewById(R.id.page1);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 1: {
                        for (ImageView imageView : imgs) {
                            imageView.setImageResource(R.mipmap.small_circle);
                        }
                        ImageView img = (ImageView) findViewById(R.id.page2);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 2: {
                        for (ImageView imageView : imgs) {
                            imageView.setImageResource(R.mipmap.small_circle);
                        }
                        ImageView img = (ImageView) findViewById(R.id.page3);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 3: {
                        for (ImageView imageView : imgs) {
                            imageView.setImageResource(R.mipmap.small_circle);
                        }
                        ImageView img = (ImageView) findViewById(R.id.page4);
                        img.setImageResource(R.mipmap.large_circle);
                        break;
                    }
                    case 4: {
                        for (ImageView imageView : imgs) {
                            imageView.setImageResource(R.mipmap.small_circle);
                        }
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
