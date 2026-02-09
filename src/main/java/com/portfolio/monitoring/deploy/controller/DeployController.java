package com.portfolio.monitoring.deploy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeployController {

    @GetMapping("/admin/deploy")
    public String index(){
        return "admin/deploy";
    }
}
