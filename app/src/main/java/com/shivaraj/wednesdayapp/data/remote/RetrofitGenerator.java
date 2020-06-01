package com.shivaraj.wednesdayapp.data.remote;


import com.shivaraj.wednesdayapp.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGenerator {


    private static final String BASE_URL = "https://itunes.apple.com/";

    public static <S> S createService(Class<S> serviceClass) {

        Retrofit retrofit = getRetrofit(BASE_URL);
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceForServer(Class<S> serviceClass, String url) {

        Retrofit retrofit = getRetrofit(url);
        return retrofit.create(serviceClass);
    }

    private static Retrofit getRetrofit(String baseUrl) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .cache(null);

        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {

                Request request = chain.request();

                Request.Builder newRequest = request.newBuilder();
                newRequest.header("Content-Type", "application/json");
                return chain.proceed(newRequest.build());
            }
        });


        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(new LiveDataCallAdapterFactory())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
        return builder.client(httpClient.build()).build();
    }

}
