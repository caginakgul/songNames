package com.example.mobile.mvvmrxdeneme.displaySongs;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobile.mvvmrxdeneme.R;
import com.example.mobile.mvvmrxdeneme.databinding.ActivityMainBinding;
import com.example.mobile.mvvmrxdeneme.displaySongs.SongAdapter;
import com.example.mobile.mvvmrxdeneme.displaySongs.SongViewModal;

import java.util.Observable;
import java.util.Observer;


/*
Bu sınıf veri çeken ViewModal sınıfını observe eder. Veri çekilme işlemi tamamlandığında UI'ı update eder.
 */

public class MainActivity extends AppCompatActivity implements Observer{

    private SongViewModal songViewModal;
    private ActivityMainBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setRecycler(mainActivityBinding.recViewSongs);
        setUpObserver(songViewModal);
        mainActivityBinding.tvGetSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songViewModal.getSongs();
            }
        });
    }

    /*
    this method will be fired when data is received.
    RecyclerAdapter set edilcek
     */
    @Override
    public void update(Observable observable, Object o) {
        Log.d("MainActivity","update fired");
        if(observable instanceof  SongViewModal) {
            SongAdapter songAdapter = (SongAdapter) mainActivityBinding.recViewSongs.getAdapter(); //adaptörün hangi recyclerview için set edilceğini
            SongViewModal songVM = (SongViewModal) observable; //ViewModal sınıfını observable olarak tanımlıyorsun
            songAdapter.setSongList(songVM.getSongList()); //viewmodal'daki güncel şarkı listesini çeker
        }
    }

    private void initBinding(){
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main); //layoutu set ediyor.
        songViewModal = new SongViewModal(this);
        mainActivityBinding.setSongViewModal(songViewModal);
    }

    private void setRecycler(RecyclerView listsongs){
        Log.d("MainActivity","setRecycler fired");
        SongAdapter songAdapter = new SongAdapter();
        listsongs.setAdapter(songAdapter);
        listsongs.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() { //unsubscribe to avoid any memory leaks - rx
        Log.d("MainActivity","onDestroy fired");
        songViewModal.reset();
        super.onDestroy();
    }
}
