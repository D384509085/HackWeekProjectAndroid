package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import enbledu.hackweekproject.R;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class FragmentAdd extends Fragment {
    View mView;
    Context mContext;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add, container, false);
        Button btnTest = (Button) mView.findViewById(R.id.test_button);
        Button btnTest2 = (Button) mView.findViewById(R.id.test_button2);
        imageView = (ImageView) mView.findViewById(R.id.image);
        return mView;
    }

    public FragmentAdd(Context context) {
        this.mContext = context;
    }
}
