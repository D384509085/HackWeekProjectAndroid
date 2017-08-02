package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import enbledu.hackweekproject.R;
import enbledu.hackweekproject.bean.QuestionBean;

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
        Button btnTest = (Button) mView.findViewById(R.id.buttn);
        btnTest.setOnClickListener(this);
        imageView = (ImageView) mView.findViewById(R.id.image);

        return mView;
    }

    public FragmentAdd(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.buttn: {
                ArrayList<QuestionBean> list = new ArrayList<QuestionBean>();
                list.add(new QuestionBean("whos is handsome", "duenbo"));
                list.add(new QuestionBean("who is the most handsome", "duenbo"));
                Gson gson = new Gson();
                String str = "";
                for (QuestionBean keyWordEntity : list) {
                    str+=gson.toJson(keyWordEntity);
                    str+="#";
                }
                Log.i("a",str);
                MyAsyncTask to = new MyAsyncTask();
                to.execute(str);
            }
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
            StringBuffer sb = new StringBuffer();
            String str = params[0];
            BufferedReader reader = null;
            HttpURLConnection con = null;
            OutputStream out = null;
            try {
                URL url = new URL("http://119.23.233.101:8080/xiaov-master/update?type=question");
                con = (HttpURLConnection) url.openConnection();
                // 设置允许输出，默认为false
                con.setDoOutput(true);
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                // 请求方式为POST请求
                con.setRequestMethod("POST");
                out =con.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
                Log.i("发送",str);
                bw.write(str);//把json字符串写入缓冲区中
                bw.flush();
                // 向服务端写数据

                // 获得服务端的返回数据
                InputStreamReader read = new InputStreamReader(
                        con.getInputStream());
                reader = new BufferedReader(read);
                String line ="" ;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    con.disconnect();
                }
            }
            return sb.toString();
        }
    }
}
