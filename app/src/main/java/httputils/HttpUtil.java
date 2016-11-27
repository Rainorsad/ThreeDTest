package httputils;


import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.lang.reflect.Type;

/**
 * Created by Yao on 2016/10/25.
 */
public class HttpUtil {
    private AsyncHttpClient ac;
    public HttpUtil(){
        super();
        ac = new AsyncHttpClient();
    }
    static HttpUtil httpUtil = null;
    public static HttpUtil getHttpUtil(){
        if (httpUtil == null){
            return httpUtil=new HttpUtil();
        }
        return httpUtil;
    }

    public void getHttp(String s, RequestParams params, final Type p, final Handler handler){
        ac.get(s, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                Gson gson = new Gson();
                Object o = gson.fromJson(s, p);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = o;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
}
