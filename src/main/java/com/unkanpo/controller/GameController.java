package com.unkanpo.controller;

import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import com.unkanpo.model.Type;
import com.unkanpo.repository.GameRepository;
import com.unkanpo.repository.TypeRepository;
import com.unkanpo.service.imp.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/admin/games")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private TypeRepository typeRepository;

    @ModelAttribute("types")
    public List<Type> listTypes() {
        return typeRepository.findAll();
    }

    @GetMapping("")
    public ModelAndView showListGame() {
        ModelAndView modelAndView = new ModelAndView("/game/list");
        modelAndView.addObject("listGame", gameService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateGame() {
        ModelAndView modelAndView = new ModelAndView("/game/create");
        modelAndView.addObject("theGame", new GameForm());
        modelAndView.addObject("listType", typeRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    @Transactional
    public String createGame(@ModelAttribute("gameForm") GameForm game) {
        gameService.save(game);
        return "redirect:/admin/games";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateGame(@PathVariable Long id) {
        GameForm game = gameService.findById(id);
        if (game != null) {
            ModelAndView modelAndView = new ModelAndView("/game/update");
            modelAndView.addObject("gameForm",game );
            modelAndView.addObject("listType", typeRepository.findAll());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public String updateGame(@ModelAttribute("game") GameForm game) {
        gameService.save(game);
        return "redirect:/admin/games";
    }

    @GetMapping("/delete/{id}")
    public String removeGame(@PathVariable Long id) {
        GameForm gameForm = gameService.findById(id);
        if (gameForm != null) {
            gameService.delete(gameForm.getGame());
            return "redirect:/admin/games";
        }
        return "/error_404";
    }

}