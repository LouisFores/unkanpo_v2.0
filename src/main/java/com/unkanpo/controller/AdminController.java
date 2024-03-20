package com.unkanpo.controller;

import com.unkanpo.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("")
    public String showListGeneral() {
       return "/admin/list";
    }

}
