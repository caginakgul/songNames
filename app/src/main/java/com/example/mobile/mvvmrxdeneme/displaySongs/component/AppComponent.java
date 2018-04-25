package com.example.mobile.mvvmrxdeneme.displaySongs.component;

import com.example.mobile.mvvmrxdeneme.displaySongs.SongViewModal;
import com.example.mobile.mvvmrxdeneme.displaySongs.request.SongServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mobile on 9.04.2018.
 * Bu sınıf işin beyni. Hangi modüllerin nereye inject edileceğini dagger framework'une anlatıyorsun.
 */

@Component(modules = {SongServiceModule.class})
@Singleton
public interface AppComponent {
    /*
    Hangi sınıfta @inject anotasyonunun kullanılacağını yazıyorsun.
     */
    void inject(SongViewModal songViewModal);
}
