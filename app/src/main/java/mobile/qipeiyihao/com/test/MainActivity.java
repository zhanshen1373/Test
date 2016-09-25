package mobile.qipeiyihao.com.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {
    public static final String url = "http://apidatatest.datatang.com/data/credit/queryIDCard?apikey=%s&rettype=json&encryptParam=%s";

    String apik = "78288504a7ed3451b4e5d6a3f8e15d74";

//    String str = "idCardName=梁元腾腾&idCardCode=410926199302173610";
    String str = "idCardName=梁元腾&idCardCode=410926199302173610";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x.Ext.init(getApplication());

        try {
            PBE pbe = new PBE(apik);
           String mStr =  pbe.encrypt(str);
            Log.e( "onCreate: ", mStr);
            String get = String.format(url,apik,mStr);
            RequestParams params = new RequestParams(get);
            x.http().request(HttpMethod.GET,params, new Callback.CommonCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    Log.e( "onSuccess: ",result.toString() );
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e( "onError: ","onError: " );
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
