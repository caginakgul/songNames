package com.example.mobile.mvvmrxdeneme.displaySongs.request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mobile on 23.03.2018.
 * Modül sınıfları Dagger'da inject edilen objeleri getirecek olan sınıflardır.
 */

@Module
public class SongServiceModule {


    @Provides
    @Singleton
    public SongService songService(Retrofit retrofit) {
        return retrofit.create(SongService.class);
    }

    @Provides
    @Singleton
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory) {
/*        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://itunes.apple.com/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(SongService.class);*/

        return new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
