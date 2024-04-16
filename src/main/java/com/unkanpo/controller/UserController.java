package com.unkanpo.controller;
import com.unkanpo.dto.AlertDTO;
import com.unkanpo.model.Game;
import com.unkanpo.model.GameForm;
import com.unkanpo.model.Type;
import com.unkanpo.service.imp.AccountService;
import com.unkanpo.service.imp.GameService;
import com.unkanpo.service.imp.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.unkanpo.dto.AlertStatus;
import com.unkanpo.model.User;
import com.unkanpo.service.imp.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private GameService gameService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TypeService typeService;

    private UserService userService;
    @GetMapping("")
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            modelAndView.setViewName("redirect:/users/login");
        }
        modelAndView.addObject("users", gameService.findAll());
        modelAndView.addObject("alert", new AlertDTO(AlertStatus.Warning, "Bạn cần đăng nhập trước!"));
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", user);
        modelAndView.addObject("alert", new AlertDTO(AlertStatus.None, ""));
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.checkUser(user.getUsername(), user.getPassword())) {
            modelAndView.setViewName("/layout_admin");
            session.setAttribute("userId", userService.getId(user.getUsername(), user.getPassword()));
        } else {
            modelAndView.setViewName("/user/login");
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Error, "Sai tài khoản mật khẩu!"));
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showCreateUser() {
        ModelAndView modelAndView = new ModelAndView("/user/register");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("alert", new AlertDTO(AlertStatus.None, ""));
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView createUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isNew(user.getEmail())) {
            userService.save(user);
            modelAndView.setViewName("/user/login");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Success,"Tạo tài khoản thành công!"));

        } else {
            modelAndView.setViewName("/user/register");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("alert",  new AlertDTO(AlertStatus.Error,"Email này đã được đăng ký"));
        }
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/user/update");
            modelAndView.addObject("user", user.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

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