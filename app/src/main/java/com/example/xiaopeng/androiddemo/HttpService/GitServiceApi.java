package com.example.xiaopeng.androiddemo.HttpService;

import com.example.xiaopeng.androiddemo.Bean.RepoEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiaopeng on 16/6/28.
 */
public interface GitServiceApi {
    public static final String BASE__URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<RepoEntity>> listRepos(@Path("user") String user);
}