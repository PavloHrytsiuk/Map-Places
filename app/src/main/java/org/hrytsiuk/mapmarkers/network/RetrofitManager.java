package org.hrytsiuk.mapmarkers.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import org.hrytsiuk.mapmarkers.network.rx.RxErrorCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitManager {

    private final static String BASE_URL = "http://zavtrakov.eurodir.ru/";

    private RetrofitManager() {}

    private static final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
            new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create());

    private static final OkHttpClient.Builder httpClient = getHttpClient();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxErrorCallAdapterFactory.create())
            .build();

    private static OkHttpClient.Builder getHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS);
    }
}
