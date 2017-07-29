package enbledu.hackweekproject.helper;

/**
 * Created by Administrator on 2017/7/29 0029.
 */
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;

import enbledu.hackweekproject.Entity.RobotEntity;


public class SendRobotUpdateToServer {
    private static String url="http://10.219.61.117:8080/ServerForPOSTMethod/ServletForPOSTMethod";
    public static final int SEND_SUCCESS=0x123;
    public static final int SEND_FAIL=0x124;
    private Handler handler;
    public SendRobotUpdateToServer(Handler handler) {
        // TODO Auto-generated constructor stub
        this.handler=handler;
    }
    /**
     * 通过POST方式向服务器发送数据
     * @param name 用户名
     * @param pwd  密码
     */
    public void SendDataToServer(RobotEntity robotEntity) {
        // TODO Auto-generated method stub
        final Map<String, String>map=new HashMap<String, String>();
        map.put("postType", "UpdateRobot");
        map.put("name", robotEntity.getName());
        map.put("qqId", String.valueOf(robotEntity.getQqID()));
        map.put("character", String.valueOf(robotEntity.getCharacter()));
        map.put("stopSpeakingNum", String.valueOf(robotEntity.getStopSpeakingNum()));
        map.put("isSpeakAuto", String.valueOf(robotEntity.isSpeakAuto()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    if (sendPostRequest(map,url,"utf-8")) {
                        handler.sendEmptyMessage(SEND_SUCCESS);//通知主线程数据发送成功
                    }else {
                        //将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 发送POST请求
     * @param map 请求参数
     * @param url 请求路径
     * @return
     * @throws Exception
     */
    private  boolean sendPostRequest(Map<String, String> param, String url,String encoding) throws Exception {
        // TODO Auto-generated method stub
        //http://10.219.61.117:8080/ServerForPOSTMethod/ServletForPOSTMethod?name=aa&pwd=124
        StringBuffer sb=new StringBuffer(url);
        if (!url.equals("")&!param.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String, String>entry:param.entrySet()) {
                sb.append(entry.getKey()+"=");
                sb.append(URLEncoder.encode(entry.getValue(), encoding));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);//删除字符串最后 一个字符“&”
        }
        byte[]data=sb.toString().getBytes();
        HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");//设置请求方式为POST
        conn.setDoOutput(true);//允许对外传输数据
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 设置窗体数据编码为名称/值对
        conn.setRequestProperty("Content-Length", data.length+"");
        OutputStream outputStream=conn.getOutputStream();//打开服务器的输入流
        outputStream.write(data);//将数据写入到服务器的输出流
        outputStream.flush();
        if (conn.getResponseCode()==200) {
            return true;
        }
        return false;
    }
}