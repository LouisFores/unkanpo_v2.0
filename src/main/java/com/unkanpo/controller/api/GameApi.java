package com.unkanpo.controller.api;

import com.unkanpo.dto.AccountDTO;
import com.unkanpo.dto.GameFormDTO;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class GameApi {
    @Autowired
    private GameService gameService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<GameFormDTO>> getGames() {
        List<GameFormDTO> gameFormDTO = new ArrayList<>();
        for (GameForm gameForm : gameService.findAll()) {
            gameFormDTO.add(gameForm.ToGameFromDTO());
        }
        return new ResponseEntity<>(gameFormDTO,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GameFormDTO> getGameById(@PathVariable Long id) {
        GameForm game = gameService.findGameById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game.ToGameFromDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountDTO>> getGameAccountsByIdGame(@PathVariable Long id) {
        Iterable<GameAccount> accounts = accountService.findByIdGame(id);
        List<AccountDTO> gameForms = new ArrayList<>();
        if (accounts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (GameAccount game : accounts) {
            gameForms.add(game.toAccountDTO());
        }
        return new ResponseEntity<>(gameForms, HttpStatus.OK);
    }
}
