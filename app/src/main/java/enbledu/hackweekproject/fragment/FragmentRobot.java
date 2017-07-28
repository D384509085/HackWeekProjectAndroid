package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import enbledu.hackweekproject.R;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class FragmentRobot extends Fragment {

    Context mContext;
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_robot, container, false);
        return mView;
    }

    public FragmentRobot(Context context) {
        this.mContext = context;
    }
}
