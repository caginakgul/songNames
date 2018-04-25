package com.example.mobile.mvvmrxdeneme.displaySongs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobile on 20.03.2018.
 */

public class SongModal {
    Integer resultCount;
    List<SongDetail> results;

    public List<SongDetail> getSongList () { return results;}

    public void setSongList(List<SongDetail> mSongList) { this.results = mSongList ;}
}
