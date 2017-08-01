package enbledu.hackweekproject.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import enbledu.hackweekproject.Entity.KeyWordEntity;
import enbledu.hackweekproject.R;

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
        /*Button btnTest = (Button) mView.findViewById(R.id.buttn);*/
        /*btnTest.setOnClickListener(this);*/
        imageView = (ImageView) mView.findViewById(R.id.image);
        return mView;
    }

    public FragmentAdd(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case  R.id.buttn: {
                ArrayList<KeyWordEntity> list = new ArrayList<KeyWordEntity>();
                list.add(new KeyWordEntity("第一个关键词",1));
                list.add(new KeyWordEntity("第二个关键词",2));
                MyAsyncTask to = new MyAsyncTask();
                to.execute(list);
            }*/
        }
    }


    class MyAsyncTask extends AsyncTask<ArrayList<KeyWordEntity>,Void,String> {






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
        protected String doInBackground(ArrayList<KeyWordEntity>... params) {
            StringBuffer sb = new StringBuffer();
            ArrayList<KeyWordEntity> p = params[0];
            BufferedReader reader = null;
            HttpURLConnection con = null;
            ObjectOutputStream oos = null;
            try {
                URL url = new URL("http://119.23.233.101:8080/xiaov-master/update?type=keyword");
                con = (HttpURLConnection) url.openConnection();
                // 设置允许输出，默认为false
                con.setDoOutput(true);
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                // 请求方式为POST请求
                con.setRequestMethod("POST");

                oos = new ObjectOutputStream(con.getOutputStream());
                // 向服务端写数据
                oos.writeObject(p);
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
                if (oos != null) {
                    try {
                        oos.close();
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
