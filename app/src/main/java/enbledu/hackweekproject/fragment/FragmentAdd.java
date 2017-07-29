package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import enbledu.hackweekproject.R;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class FragmentAdd extends Fragment {
    View mView;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add, container, false);
        return mView;
    }

    public FragmentAdd(Context context) {
        this.mContext = context;
    }
}
