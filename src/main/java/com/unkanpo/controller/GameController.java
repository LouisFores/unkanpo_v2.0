package com.unkanpo.controller;

import com.unkanpo.model.Game;
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
    private GameRepository gameRepository;
    @Autowired
    private TypeRepository typeRepository;

    @ModelAttribute("types")
    public Iterable<Type> listTypes() {
        return typeRepository.findAll();
    }

    @GetMapping("")
    public ModelAndView showListGame() {
        ModelAndView modelAndView = new ModelAndView("/game/list");
        List<Game> games = (List<Game>) gameRepository.findAll();
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            Set<Type> types = gameService.getTypesByGameId(game.getIdGame());
            game.setTypes(types);
            games.set(i, game);
        }
//
        modelAndView.addObject("games", games);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateGame() {
        ModelAndView modelAndView = new ModelAndView("/game/create");
        modelAndView.addObject("game", new Game());
        return modelAndView;
    }

    @PostMapping("/create")
    @Transactional
    public String createGame(@ModelAttribute("Game") Game game) {
        Set<Type> typeSet = new HashSet<>();
        for (Type type : game.getTypes()) {
            System.out.println(type.getNameType());
            typeSet.add(type);
        }
        game.setTypes(typeSet);
        gameRepository.save(game);
        return "redirect:/admin/games";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateGame(@PathVariable Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/game/update");
            modelAndView.addObject("game", game.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public String updateGame(@ModelAttribute("game") Game game) {
        gameRepository.save(game);
        return "redirect:/admin/games";
    }

    @GetMapping("/remove/{id}")
    public String removeGame(@PathVariable Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (!gameOptional.isPresent()) {
            return "/error_404";
        }
        gameRepository.delete(gameOptional.get());
        return "redirect:/admin/games";
    }

}
