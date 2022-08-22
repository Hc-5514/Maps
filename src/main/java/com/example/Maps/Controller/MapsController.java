package com.example.Maps.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapsController {

    @GetMapping("/")
    public String Maps() {
        return "maps";
    }

}
