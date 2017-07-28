package enbledu.hackweekproject.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import enbledu.hackweekproject.R;
import enbledu.hackweekproject.helper.DepthPageTransformer;

public class MainActivity extends AppCompatActivity {
    private ViewPager mvViewPager;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initViewPage() {

        // 将要分页显示的View装入数组中
        List<Fragment> list = new ArrayList<Fragment>();
        /*list.add(fragmentTodoList);
        list.add(fragment2);*/
        mvViewPager = (ViewPager) findViewById(R.id.pager);
        mvViewPager.setPageTransformer(true, new DepthPageTransformer());
        mvViewPager.setCurrentItem(0);
        mvViewPager.setOffscreenPageLimit(5);
    }
}
