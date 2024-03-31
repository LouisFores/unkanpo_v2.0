package com.unkanpo.controller;

import com.unkanpo.model.GameAccount;
import com.unkanpo.service.imp.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ModelAndView getListAccount() {
        ModelAndView modelAndView = new ModelAndView("/account/list");
        modelAndView.addObject("accounts", accountService.findAll());
        return modelAndView;
    }

    @GetMapping("/admin/account/create")
    public ModelAndView createAccountForm() {
        ModelAndView modelAndView = new ModelAndView("/account/create");
        modelAndView.addObject("account", new GameAccount());
        return modelAndView;
    }

    @PostMapping("/admin/account/create")
    public ModelAndView createAccount() {
        ModelAndView modelAndView = new ModelAndView();

    }
}
