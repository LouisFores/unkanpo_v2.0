package com.unkanpo.controller;
import com.unkanpo.model.User;
import com.unkanpo.service.imp.GameService;
import com.unkanpo.service.imp.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    @GetMapping("")
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            modelAndView.setViewName("redirect:/users/login");
        }
        modelAndView.addObject("users", gameService.findAll());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateUser() {
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
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


}
