package com.unkanpo.controller;


import com.unkanpo.model.GameAccount;
import com.unkanpo.service.imp.AccountService;
import com.unkanpo.service.imp.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private GameService gameService;

    @GetMapping("")
    public ModelAndView getListAccount() {
        ModelAndView modelAndView = new ModelAndView("/account/list");
        modelAndView.addObject("accounts", accountService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createAccountForm() {
        ModelAndView modelAndView = new ModelAndView("/account/create");
        modelAndView.addObject("account", new GameAccount());
        modelAndView.addObject("games", gameService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createAccount(@ModelAttribute("account") GameAccount account) {
        ModelAndView modelAndView = new ModelAndView();
        if (accountService.IsExist(account)) {
            modelAndView.setViewName("/account/create");
            modelAndView.addObject("account", account);
            modelAndView.addObject("error", "account is exits");
        } else {
            accountService.save(account);
            modelAndView.setViewName("redirect:/admin/account");
        }
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateAccount(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/account/update");
        modelAndView.addObject("account", accountService.findById(id).get());
        modelAndView.addObject("games", gameService.findAll());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateAccount(@ModelAttribute("account") GameAccount account) {
        ModelAndView modelAndView = new ModelAndView("/account/update");
        accountService.save(account);
        modelAndView.addObject("account", account);
        modelAndView.addObject("games", gameService.findAll());
        modelAndView.addObject("alert", "sửa thành công");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteAccount(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/account");
        accountService.delete(id);
        return modelAndView;
    }

}
