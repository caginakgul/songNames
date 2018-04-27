package com.example.mobile.mvvmrxdeneme.displaySongs;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile.mvvmrxdeneme.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by mobile on 22.03.2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongAdapterViewHolder>{

    private List<SongDetail> songList;

    public SongAdapter() {this.songList = Collections.emptyList();}

    @Override
    public SongAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new SongAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SongAdapter.SongAdapterViewHolder holder, int position) {
        SongDetail songDetail = songList.get(position);
        holder.bindData(songDetail);
    }

    @Override
    public int getItemCount() {
        if(songList!=null){
            Log.d("getItemCOunt: ",String.valueOf(songList.size()));
        }
        else{
            Log.d("getItemCOunt: ","SONGLIST IS NULL");
        }

        return songList.size();
    }

    public void setSongList(List<SongDetail> songList) {
        this.songList = songList;
        notifyDataSetChanged(); //ekran değişmeden ekranda gösterilecek datayı değiştirmek için
    }

    public static class SongAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvArtistName;


        public SongAdapterViewHolder(View view) {
            super(view);
            tvArtistName = view.findViewById(R.id.tvSongNameDynamic);
        }

        public void bindData(final SongDetail songDetail) {
            tvArtistName.setText(songDetail.getArtistName());
        }

    }

}
