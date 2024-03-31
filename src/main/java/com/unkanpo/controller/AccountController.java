package com.unkanpo.controller;

import com.unkanpo.service.imp.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.PublicKey;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("")
    public ModelAndView listAccounts() {
        ModelAndView model = new ModelAndView("/account/list");
        model.addObject("listAccount", accountService.findAll());
        return model;
    }

}
