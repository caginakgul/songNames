package com.example.mobile.mvvmrxdeneme.displaySongs;

/**
 * Created by mobile on 20.03.2018.
 */

public class SongDetail {
    String wrapperType;
    String kind;
    Integer artistId;
    Integer collectionId;
    Integer trackId;
    String artistName;
    String collectionName;
    String trackName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
