package com.unkanpo.controller;


import com.unkanpo.model.Game;
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
        modelAndView.addObject("games", gameService.findAll());
        return modelAndView;
    }

    @GetMapping("/findListOfGame/{idGame}/{where}")
    public ModelAndView getListAccountOfGame(@PathVariable Long idGame, @PathVariable String where) {
        ModelAndView modelAndView = new ModelAndView();
        Game game = idGame != 0 ? gameService.findById(idGame).getGame() : null;

        switch (where) {
            case "create":
                modelAndView.setViewName("account/create");
                modelAndView.addObject("account", new GameAccount());
                break;
            case "update":
                modelAndView.setViewName("account/update");
                modelAndView.addObject("account", accountService.findById(1L).get());
                break;
            case "list":
                modelAndView.setViewName("account/list");
                modelAndView.addObject("accounts", accountService.findAll());
                break;
            default:
                return null;
        }

        modelAndView.addObject("games", gameService.findAll());

        if (idGame == 0) {
            modelAndView.addObject("accounts", accountService.findAll());
        } else {
            modelAndView.addObject("accounts", accountService.findAllByGame(game));
        }

        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createAccountForm() {
        ModelAndView modelAndView = new ModelAndView("/account/create");
        modelAndView.addObject("account", new GameAccount());
        modelAndView.addObject("accounts", accountService.findAll());
        modelAndView.addObject("games", gameService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createAccount(@ModelAttribute("account") GameAccount account) {
        ModelAndView modelAndView = new ModelAndView();
        if (accountService.IsExist(account)) {
            modelAndView.setViewName("/account/create");
            modelAndView.addObject("account", new GameAccount());
            modelAndView.addObject("accounts", accountService.findAll());
            modelAndView.addObject("games", gameService.findAll());
            modelAndView.addObject("error", "Account is exits");
        } else {
            accountService.save(account);
            modelAndView.setViewName("redirect:/admin/account/create");
        }
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateAccount(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/account/update");
        modelAndView.addObject("account", accountService.findById(id).get());
        modelAndView.addObject("accounts", accountService.findAll());
        modelAndView.addObject("games", gameService.findAll());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateAccount(@ModelAttribute("account") GameAccount account) {
        ModelAndView modelAndView = new ModelAndView("/account/update");
        accountService.save(account);
        modelAndView.addObject("account", account);
        modelAndView.addObject("accounts", accountService.findAll());
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
    @GetMapping("/deleteInCreate/{id}")
    public ModelAndView deleteInCreateAccount(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/account/create");
        accountService.delete(id);
        return modelAndView;
    }

}
