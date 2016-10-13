package com.example.nagato.githubnewsprovider.net;

import com.example.nagato.githubnewsprovider.utils.DateUtil;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nagato hanj
 */
public class GankAPIService {
    private static volatile GankAPI sGankAPI;
    private static final Gson gson = new GsonBuilder()
            .setDateFormat(DateUtil.DATE_FORMAT_WHOLE)
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();

    private static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build();

    private static final Retrofit girlsRetrofit = new Retrofit.Builder()
            .baseUrl(GankAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build();


    private GankAPIService() {
    }

    public static GankAPI getInstance() {
        if (sGankAPI == null) {
            synchronized (GankAPIService.class) {
                if (sGankAPI == null)
                    sGankAPI = girlsRetrofit.create(GankAPI.class);
            }
        }

        return sGankAPI;
    }

}
