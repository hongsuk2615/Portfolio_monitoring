package com.portfolio.monitoring.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {

    @GetMapping("/admin/dashboard")
    public String index(){
        return "admin/dashboard";
    }
}
