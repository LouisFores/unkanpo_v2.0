package com.unkanpo.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unkanpo.model.GameAccount;
import com.unkanpo.model.GameForm;
import com.unkanpo.service.imp.AccountService;
import com.unkanpo.service.imp.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.io.InputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/v1/games")
public class GameApi {
    @Autowired
    private GameService gameService;
    @Autowired
    private AccountService accountService;


    @GetMapping
    public ResponseEntity<List<GameForm>> getGames() {
        return new ResponseEntity<>(gameService.findAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GameForm> getGameById(@PathVariable Long id) {
        GameForm game = gameService.findGameById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<Iterable<GameAccount>> getGameAccountsByIdGame(@PathVariable Long id) {
        Iterable<GameAccount> accounts = accountService.findByIdGame(id);
        if (accounts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/accounts/{idAccount}")
    public ResponseEntity<Iterable<GameAccount>> getGameAccountById(@PathVariable Long id,@PathVariable Long idAccount) {
        Iterable<GameAccount> accounts = accountService.findByIdGame(idAccount);
        if (accounts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }



}
