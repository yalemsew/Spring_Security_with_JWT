package org.example.spring_security.secured;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @RequestMapping
    public String admin(){
        return "admin:secured end point";
    }
}
