package com.experis.mytunesassignment.viewControllers;

import com.experis.mytunesassignment.data_access.interfaces.IMusicService;
import com.experis.mytunesassignment.data_access.services.MusicService;
import com.experis.mytunesassignment.models.MusicSearchInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class HomeController {

    IMusicService musicService = new MusicService();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("searchQuery", "");
        model.addAttribute("randomMusicInfo", musicService.getRandomMusicInfo());

        // might need to add something here related to search?

        return "home";
    }

    @PostMapping("/")
    public String handleSearch(@RequestParam("searchQuery") String searchQuery) {
        System.out.println("Search query: " + searchQuery);

        return "redirect:/result?searchQuery=" + searchQuery;
    }
}
