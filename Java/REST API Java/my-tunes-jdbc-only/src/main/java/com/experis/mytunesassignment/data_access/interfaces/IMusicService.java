package com.experis.mytunesassignment.data_access.interfaces;

import com.experis.mytunesassignment.models.MusicInfo;
import com.experis.mytunesassignment.models.MusicSearchInfo;

import java.util.ArrayList;

public interface IMusicService {
    /**
     * @return An ArrayList with MusicInfo.
     */
    ArrayList<MusicInfo> getRandomMusicInfo();

    /**
     * @return An ArrayList with MusicSearchInfo.
     */
    ArrayList<MusicSearchInfo> getMusicSearchInfo(String searchQuery);


}
