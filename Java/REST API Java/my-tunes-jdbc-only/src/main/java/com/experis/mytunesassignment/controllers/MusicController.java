package com.experis.mytunesassignment.controllers;

import com.experis.mytunesassignment.data_access.interfaces.IMusicService;
import com.experis.mytunesassignment.data_access.services.MusicService;
import com.experis.mytunesassignment.models.MusicInfo;
import com.experis.mytunesassignment.models.MusicSearchInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api")
public class MusicController {
    IMusicService musicService = new MusicService();

    @GetMapping("musicinfo/random")
    public ArrayList<MusicInfo> getRandomMusicInfo(){
        return musicService.getRandomMusicInfo();
    }

    @GetMapping("musicinfo/search")
    public  ArrayList<MusicSearchInfo> getMusicSearchInfo(@RequestParam String search){ return musicService.getMusicSearchInfo(search);}
}
