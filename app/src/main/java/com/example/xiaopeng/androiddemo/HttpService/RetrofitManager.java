package com.example.xiaopeng.androiddemo.HttpService;

import android.util.Log;

import com.example.xiaopeng.androiddemo.Application.MainApplication;
import com.example.xiaopeng.androiddemo.Utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaopeng on 16/6/24.
 */
public class RetrofitManager {


    //短缓存有效期为1分钟
    public static final int CACHE_STALE_SHORT = 60;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;
    // retrofit
    private Retrofit retrofit;
    //okHTTPClient
    private OkHttpClient mOkHttpClient;

    private static RetrofitManager instance = null;


    // singleton RetrofitNanager instance
    public static synchronized RetrofitManager getInstance() {
        if (instance == null){
            instance = new RetrofitManager();
        }

        return instance;
    }

    /**
     *
     * @author xiaopeng
     *
     *  create at 16/6/27 上午10:05
     *
     * build the retrofit manager with okHttp
     */

    public void builder(){
        initOkHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl(GitServiceApi.BASE__URL)
                .client(mOkHttpClient)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     *
     * @author xiaopeng
     *
     *  create at 16/6/27 上午10:05
     *
     * 初始化 okHttp
     */

    private void initOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                Log.d("OkHttp: ", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {

                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(MainApplication.getContext().getCacheDir(), "HttpCache"),
                            1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(loggingInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    /**
     * 
     * @author xiaopeng
     *
     *  create at 16/6/27 上午10:04
     *
     * 配置缓存策略
     */
    
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
                        .removeHeader("Pragma").build();
            }
        }
    };

    public GitServiceApi getGitService(){
        return retrofit.create(GitServiceApi.class);
    }
}
