package me.xunhou.v2ex.client;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.persistence.Constant;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by ihgoo on 2015/5/18.
 */
public class Clenit {

    public static String API_URL = Constant.API_URL;

    public static ExecutorService mExecutorService;


    public static void main(String arg[]) {

        VApi vApi = getServiceClient();
//        vApi.getForumDetail("192495",new Callback<Response>() {
//            @Override
//            public void success(Response res, Response response) {
//                try {
//                    InputStream in = res.getBody().in();
//                    String responseString = StringUtil.inputStream2String(in);
//                    PaserFourmDetail.paserFourmDetail(responseString);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });


//        Login login = new Login();
//        login.getOnce();
//        vApi.getOnce(new Callback<Response>() {
//            @Override
//            public void success(Response res, Response response) {
//                try {
//                    InputStream in = res.getBody().in();
//                    String responseString = StringUtil.inputStream2String(in);
//                    String once = PaserLogin.paserOnce(responseString);
//                    //next=%2F&u=aa&once=59483&p=aa
//                    String once = PaserLogin.paserOnce(responseString);
//                    Login.login("ihgoo","HUKAIJUN123",once);
//
//
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });


    }


    public static VApi getServiceClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(100, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(100, TimeUnit.SECONDS);

        RestAdapter.Builder restAdapter = new RestAdapter.Builder();
        ApiHeaders apiHeaders = new ApiHeaders();
        restAdapter.setRequestInterceptor(apiHeaders);

        restAdapter.setEndpoint(API_URL);
        restAdapter.setClient(new OkClient(okHttpClient));
        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        mExecutorService = Executors.newCachedThreadPool();
        return restAdapter
                .build()
                .create(VApi.class);
    }

    public static void stopAll() {
        List<Runnable> pendingAndOngoing = mExecutorService.shutdownNow();
    }

}
