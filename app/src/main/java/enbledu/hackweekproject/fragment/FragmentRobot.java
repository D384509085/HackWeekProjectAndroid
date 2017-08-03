package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.KeywordEntity;
import enbledu.hackweekproject.Entity.RobotEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.database.KeywordDAOImpl;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class FragmentRobot extends Fragment {

    private KeywordDAOImpl mKeywordDao;
    private Context mContext;
    RobotEntity mRobotEntity;
    View mView;
    TextView qqText;
    TextView isSpeakAutoText;
    TextView keywourdText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_robot, container, false);
        init();
        return mView;
    }

    private void init() {
        qqText = (TextView) mView.findViewById(R.id.qq_number);
        isSpeakAutoText = (TextView) mView.findViewById(R.id.robot_canspeak);
        keywourdText = (TextView) mView.findViewById(R.id.robot_keywourd);
        mKeywordDao = new KeywordDAOImpl(mContext);
    }

    public FragmentRobot(Context context, RobotEntity robotEntity) {
        this.mContext = context;
        this.mRobotEntity = robotEntity;
    }

    @Override
    public void onResume() {
        initDatas();
        super.onResume();
    }

    private void initDatas() {
        qqText.setText(String.valueOf(mRobotEntity.getQqID()));

        if (mRobotEntity.isSpeakAuto()) {
            isSpeakAutoText.setText("我能主动说话");
        } else {
            isSpeakAutoText.setText("我不能主动说话");
        }
        String keyword = "";
        ArrayList<KeywordEntity> list = mKeywordDao.getKeyWord();
        if (list!=null) {

            for (KeywordEntity keywordEntity : list) {
                keyword +=keywordEntity.getKeyWord()+"，";
            }
            keywourdText.setText(keyword);
        }
    }
}
