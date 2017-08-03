package enbledu.hackweekproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import enbledu.hackweekproject.Entity.QuestionEntity;
import enbledu.hackweekproject.R;
import enbledu.hackweekproject.database.QuestionDAOImpl;
import enbledu.hackweekproject.helper.RecyclerItemClickListener;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class QuestionRecycleViewAdapter extends RecyclerView.Adapter<QuestionRecycleViewAdapter.MyViewHolder>{

    private RecyclerItemClickListener.OnItemClickListener mListener;
    private LayoutInflater mInflater;
    private QuestionDAOImpl mDAO;
    private Context mContext;
    private List<QuestionEntity> questionDatas;

    public QuestionRecycleViewAdapter(Context context, List<QuestionEntity> questionDatas) {
        mInflater = LayoutInflater.from(context);
        mDAO = new QuestionDAOImpl(context);
        this.mContext = context;
        this.questionDatas = questionDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.question_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        QuestionEntity mQuestionEntity = questionDatas.get(position);
        holder.questionOneText.setText(mQuestionEntity.getQuestion1());
        holder.questionTwoText.setText(mQuestionEntity.getQuestion2());
        holder.questionThreeText.setText(mQuestionEntity.getQuestion3());
        holder.answer.setText(mQuestionEntity.getAnswer());

        final MyViewHolder vh = (MyViewHolder) holder;
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, position);
                }
            }
        });
        vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null) {
                    mListener.onItemLongClick(v, position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView questionOneText;
        TextView questionTwoText;
        TextView questionThreeText;
        TextView answer;
        public MyViewHolder(View itemView) {
            super(itemView);
            questionOneText = (TextView) itemView.findViewById(R.id.question_one);
            questionTwoText = (TextView) itemView.findViewById(R.id.question_two);
            questionThreeText = (TextView) itemView.findViewById(R.id.question_three);
            answer = (TextView) itemView.findViewById(R.id.answer);
        }
    }
}
