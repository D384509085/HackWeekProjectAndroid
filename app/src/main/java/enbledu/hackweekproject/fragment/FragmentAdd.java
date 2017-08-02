package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import enbledu.hackweekproject.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static javax.net.ssl.SSLEngineResult.Status.OK;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class FragmentAdd extends Fragment implements View.OnClickListener{
    View mView;
    Context mContext;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_add, container, false);
       /* Button btnTest = (Button) mView.findViewById(R.id.buttn);
        btnTest.setOnClickListener(this);*/
        imageView = (ImageView) mView.findViewById(R.id.image);

        return mView;
    }

    public FragmentAdd(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case  R.id.buttn: {
                ArrayList<QuestionBean> list = new ArrayList<QuestionBean>();
                list.add(new QuestionBean("handsome duenbo",".."));
                list.add(new QuestionBean("duenbo is handsome", "ss"));
                Gson gson = new Gson();
                String str = "";
                for (QuestionBean keyWordEntity : list) {
                    str+=gson.toJson(keyWordEntity);
                    str+="#";
                }
                MyAsyncTask to = new MyAsyncTask();
                to.execute(str);
            }*/
        }
    }


    class MyAsyncTask extends AsyncTask<String,Void,String> {






        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null && OK.equals(result)) {
                Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        //真正耗时操作
        @Override
        protected String doInBackground(String... params) {
            String str = params[0];
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/octet-stream");
            RequestBody body = RequestBody.create(mediaType, str);
            Request request = new Request.Builder()
                    .url("http://119.23.233.101:8080/xiaov-master/update?type=question")
                                    .post(body)
                                    .addHeader("cache-control", "no-cache")
                                    .addHeader("postman-token", "1d894b75-b767-bb65-93c1-67ff05e7a9f7")
                                    .build();

            try {
                Response response = client.newCall(request).execute();
                Log.i("s",response.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return request.toString();
        }

    }
}
