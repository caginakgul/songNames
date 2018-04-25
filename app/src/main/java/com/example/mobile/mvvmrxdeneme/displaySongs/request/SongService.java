package com.example.mobile.mvvmrxdeneme.displaySongs.request;

import com.example.mobile.mvvmrxdeneme.displaySongs.SongModal;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mobile on 23.03.2018.
 */

public interface SongService {
    @GET
    Observable<SongModal> fetchSongs(@Url String url);

}
