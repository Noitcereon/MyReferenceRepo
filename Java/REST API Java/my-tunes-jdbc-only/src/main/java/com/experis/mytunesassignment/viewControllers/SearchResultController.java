package com.experis.mytunesassignment.viewControllers;

import com.experis.mytunesassignment.data_access.interfaces.IMusicService;
import com.experis.mytunesassignment.data_access.services.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchResultController {
    IMusicService musicService = new MusicService();

    @GetMapping("/result")
    public String result(Model model, @RequestParam String searchQuery){
        model.addAttribute("musicSearchInfo", musicService.getMusicSearchInfo(searchQuery));
        model.addAttribute("searchQuery", searchQuery);
        return "search-result";
    }
}
