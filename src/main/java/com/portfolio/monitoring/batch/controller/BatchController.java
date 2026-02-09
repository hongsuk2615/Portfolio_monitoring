package com.portfolio.monitoring.batch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BatchController {

    @GetMapping("/admin/batch")
    public String index(){
        return "admin/batch";
    }
}
