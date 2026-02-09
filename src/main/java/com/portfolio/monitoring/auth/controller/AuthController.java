package com.portfolio.monitoring.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/admin/login")
    public String login(){

        if (isAuthenticated()) {
            return "redirect:/admin/dashboard";
        }
        return "admin/login";
    }


    /**
     * 사용자 로그인 여부 체크
     */
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String); // "anonymousUser"가 아닌지 확인
    }

}
