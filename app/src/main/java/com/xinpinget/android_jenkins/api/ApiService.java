package com.xinpinget.android_jenkins.api;

import com.xinpinget.android_jenkins.domain.ApiJsonRoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cc on 2/15/16.
 */
public class ApiService {


    private final Retrofit mRetrofit;



    private ApiService(String hostName) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(hostName)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService create(String hostName) {
        if (hostName.indexOf(hostName.length() - 1) != '/') {
            hostName = hostName + '/';
        }
        return new ApiService(hostName);
    }

    public Jenkins jenkins() {
        return mRetrofit.create(Jenkins.class);
    }

    public interface Jenkins {
        @GET("/api/json")
        Call<ApiJsonRoot> list();
    }
}
