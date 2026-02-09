package com.portfolio.monitoring.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthController {

    @GetMapping("/admin/health")
    public String index(){
        return "admin/health";
    }
}
