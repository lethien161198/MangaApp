package com.example.mangaapp.modules.api;

import com.example.mangaapp.common.Utilities;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static final HttpLoggingInterceptor interceptor1 = new HttpLoggingInterceptor();
    private static final OkHttpClient.Builder client1 = new OkHttpClient.Builder()
            .addInterceptor(interceptor1);
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Utilities.baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        interceptor1.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit retrofit1 = builder.client(client1.build()).build();

        return retrofit1.create(serviceClass);
    }


}
