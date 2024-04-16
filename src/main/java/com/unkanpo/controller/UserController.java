package com.unkanpo.controller;

import com.unkanpo.dto.AccountDTO;
import com.unkanpo.dto.AlertDTO;
import com.unkanpo.dto.GameFormDTO;
import com.unkanpo.model.Game;
import com.unkanpo.model.GameAccount;
import com.unkanpo.model.GameForm;
import com.unkanpo.model.Type;
import com.unkanpo.service.imp.AccountService;
import com.unkanpo.service.imp.GameService;
import com.unkanpo.service.imp.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private GameService gameService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TypeService typeService;
    @ModelAttribute("types")
    public List<Type> listTypes() {
        return typeService.findAll();
    }
    @GetMapping("/home")
    public ModelAndView getGamesUser() {
        ModelAndView modelAndView = new ModelAndView("user/shop");
        List<GameForm> games = gameService.findAll();
        modelAndView.addObject("listGame", gameService.findAll());
        modelAndView.addObject("alert", new AlertDTO());
        return modelAndView;
    }

    @GetMapping("/findListOfGame/{idGame}")
    public ModelAndView getListAccountOfGame(@PathVariable Long idGame) {
        ModelAndView modelAndView = new ModelAndView("/user/list_account_game");
        Game game = gameService.findById(idGame).getGame();
        modelAndView.addObject("accounts", accountService.findAllByGame(game));
        return modelAndView;
    }
}