package com.example.mobile.mvvmrxdeneme.displaySongs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import android.databinding.ObservableInt;

import com.example.mobile.mvvmrxdeneme.displaySongs.app.AppController;
import com.example.mobile.mvvmrxdeneme.displaySongs.request.SongService;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by mobile on 20.03.2018.
 */

public class SongViewModal extends Observable {

    /*
    Observe edilebilen ui componentleri tanımlıyorsun.
    ObservableInt değişken tipini kullanabilmek için gradle'da enable=true diyerek izin vermen gerekli.
    ui componentler public tanımlanır.
     */
    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt tvGetSongs;
    private List<SongDetail> songList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /*
        Burada artık modülü değil, inject edeceğin sınıfı yazarsın.
     */
    @Inject
    SongService songService;


    /*
        init ve başlangıçtaki visibility durumları set ediliyor.
     */
    public SongViewModal(@NonNull Context context) {
        Log.d("SongViewModal","viewModal Constructor fired");
        this.context = context;
        this.songList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        tvGetSongs = new ObservableInt(View.VISIBLE);
    }

    public void getSongs(){
        final AppController appController = AppController.create(context);
      //  SongService songService = appController.getSongService(); //MANUAL DI NO NEED WITH DAGGER


        /*
        Request'i yapan Retrofit (SongServiceModule). Rx ise bu web request işlemini dinler.
         */
        appController.getAppComponent().inject(this);
        Disposable disposable = songService.fetchSongs("search?term=chao&limit=5/") //Disposable observable yerine kullanılır
                .subscribeOn(appController.subscribeScheduler()) //Schedulers.io'ya subscribe oluyor. Scheduler io -> I/O Thread (doInBackground) start process
                .observeOn(AndroidSchedulers.mainThread()) //observe the result on mainThread - similiar with onPostExecute
                .subscribe(new Consumer<SongModal>() {
                    @Override public void accept(SongModal songModal) throws Exception { //request başarılı bir şekilde tamamlandı
                        updateSongList(songModal.getSongList());
                        progressBar.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                        tvGetSongs.set(View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception { //request hata ile sonlandı
     //                   Toast.makeText(appController.getBaseC,"Uups, birşeyler ters gitti...");
                        progressBar.set(View.GONE);
                        userRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);

    }

    public List<SongDetail> getSongList() {
        return songList;
    }

    /*
        Gözlemlenen obje olan songList objesinde değişiklik meydana geldi. Bunu tüm observerlara (activity'e) bildir.
     */
    private void updateSongList(List<SongDetail> songs) {
        Log.d("SongViewModal","updateSongList fired");
        songList.addAll(songs);
        setChanged();
        notifyObservers();
    }

    public void reset(){ //subscription kill ediliyor. activity lifecycle'dan sonra memoryde gereksiz bir field kalmaması için
        Log.d("SongViewModal","reset fired");
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable=null;
        context=null;

        songList=null;
    }

}
