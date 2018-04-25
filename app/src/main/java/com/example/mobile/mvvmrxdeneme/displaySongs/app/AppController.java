package com.example.mobile.mvvmrxdeneme.displaySongs.app;

import android.app.Application;
import android.content.Context;

import com.example.mobile.mvvmrxdeneme.displaySongs.component.AppComponent;

import com.example.mobile.mvvmrxdeneme.displaySongs.component.DaggerAppComponent;
import com.example.mobile.mvvmrxdeneme.displaySongs.request.SongService;
import com.example.mobile.mvvmrxdeneme.displaySongs.request.SongServiceModule;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mobile on 26.03.2018.
 */

public class AppController extends Application {
    private SongService songService; //retrofit things
    private Scheduler scheduler; //Rx object
    private static AppComponent appComponent;

    private static AppController get(Context context){
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

   /* public SongService getSongService() {
        if (songService == null) {
            songService = SongServiceModule.songService();
        }
        return songService;
    }*/

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    //DI ile buna gerek kalmayacak.Inject edip geçiceksin
    public void setUserService(SongService songService) {
        this.songService = songService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent buildAppComponent() {
        return DaggerAppComponent.builder().songServiceModule(new SongServiceModule()).build();
    }

}
