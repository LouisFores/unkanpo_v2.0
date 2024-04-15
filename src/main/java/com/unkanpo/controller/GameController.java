package com.unkanpo.controller;

import com.unkanpo.dto.AlertDTO;
import com.unkanpo.dto.AlertStatus;
import com.unkanpo.model.GameForm;
import com.unkanpo.model.Type;
import com.unkanpo.service.imp.GameService;
import com.unkanpo.service.imp.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin/games")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private TypeService typeService;

    @ModelAttribute("types")
    public List<Type> listTypes() {
        return typeService.findAll();
    }

    @GetMapping("")
    public ModelAndView showListGame() {
        ModelAndView modelAndView = new ModelAndView("/game/list");
        modelAndView.addObject("listGame", gameService.findAll());
        modelAndView.addObject("alert", new AlertDTO());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateGame() {
        ModelAndView modelAndView = new ModelAndView("/game/create");
        modelAndView.addObject("theGame", new GameForm());
        modelAndView.addObject("listType", typeService.findAll());
        modelAndView.addObject("alert", new AlertDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    @Transactional
    public ModelAndView createGame(@ModelAttribute("gameForm") GameForm game, @RequestParam("more-image") List<MultipartFile> image) {
        ModelAndView modelAndView = new ModelAndView("/game/create");
        if (gameService.checkNameGameDuplicate(game.getGame().getNameGame().trim())) {
            return backGameForm(modelAndView, game, "Tên game đã tồn tại");
        }
        try {
            gameService.save(game, image);
            modelAndView.setViewName("/game/list");
            modelAndView.addObject("listGame", gameService.findAll());
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Success,"Tạo mới thành công"));
        } catch (IOException e) {
            modelAndView = backGameForm(modelAndView, game, e.getMessage());
        }finally {
            return modelAndView;
        }
    }

    private ModelAndView backGameForm(ModelAndView view, GameForm game, String message) {
        ModelAndView modelAndView = view;
        modelAndView.addObject("theGame", game);
        modelAndView.addObject("listType", typeService.findAll());
        modelAndView.addObject("alert", new AlertDTO(AlertStatus.Error, message));
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateGame(@PathVariable Long id) {
        GameForm game = gameService.findGameById(id);
        ModelAndView modelAndView = new ModelAndView("/game/update");

        if (game != null) {
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Success,"Sẵn sàng để cập nhaatj"));
        } else {
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Error, "Không tìm thấy game"));
        }

        String background = "/image/" + game.getGame().getNameGame().trim().replace(' ', '-') + ".jpg";
        modelAndView.addObject("gameForm", game);
        modelAndView.addObject("listType", typeService.findAll());
        modelAndView.addObject("backGround", background);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateGame(@ModelAttribute("game") GameForm game, @RequestParam("more-image") List<MultipartFile> image) {
        ModelAndView modelAndView = new ModelAndView("/game/update");
        if (gameService.checkNameGameUpdate(game.getGame().getIdGame(), game.getGame().getNameGame())) {
            if (gameService.checkNameGameDuplicate(game.getGame().getNameGame().trim())) {
                String background = "/image/" + game.getGame().getNameGame().trim().replace(' ', '-') + ".jpg";
                modelAndView.addObject("gameForm", game);
                modelAndView.addObject("listType", typeService.findAll());
                modelAndView.addObject("backGround", background);
                return backGameForm(modelAndView, game, "Tên game đã tồn tại, đây là hình ảnh game đó");
            }
        }

        try {
            if (image.get(0).getOriginalFilename().equals("")) {
                gameService.save(game);
            } else {
                gameService.save(game, image);
            }
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Success, "Sửa thành công!"));
        } catch (IOException e) {
            modelAndView = backGameForm(modelAndView, game, e.getMessage());
        } catch (Exception e) {
            modelAndView = backGameForm(modelAndView, game, e.getMessage());
        }finally {
            modelAndView.addObject("gameForm", game);
            modelAndView.addObject("listType", typeService.findAll());
            String background = "/image/" + game.getGame().getNameGame().trim().replace(' ', '-') + ".jpg";
            modelAndView.addObject("backGround", background);
            return modelAndView;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView removeGame(@PathVariable Long id) {
        ModelAndView modelAndView = showListGame();
        GameForm gameForm = gameService.findGameById(id);
        if (gameForm != null) {
            gameService.delete(gameForm.getGame());
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Success, "Xóa thành công!"));
        }else {
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Error,"Không tìm thấy game muốn xóa!"));
        }
        return modelAndView;
    }


    @GetMapping("/filter")
    public ModelAndView searchByName(@RequestParam("keyword") String keyword) {
        ModelAndView modelAndView = new ModelAndView("/game/list");
        modelAndView.addObject("listGame", gameService.findAllByName_game(keyword));
        modelAndView.addObject("alert", new AlertDTO());
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/game/information");
        modelAndView.addObject("gameForm", gameService.findById(id));
        modelAndView.addObject("alert", new AlertDTO());
        return modelAndView;
    }

    @GetMapping("/{choice}")
    public ModelAndView testChoice(@PathVariable("choice") int choice) {
        ModelAndView modelAndView = new ModelAndView("/alert");
        switch (choice) {
            case 1:
                modelAndView.addObject("alert", new AlertDTO(AlertStatus.Error,"error case"));break;
            case 2:
                modelAndView.addObject("alert", new AlertDTO(AlertStatus.Warning,"warning case"));break;
            case 3:
                modelAndView.addObject("alert", new AlertDTO(AlertStatus.Success,"supper long case"));break;
        }
        return modelAndView;
    }
}