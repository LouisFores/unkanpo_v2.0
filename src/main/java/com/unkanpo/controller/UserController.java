package com.unkanpo.controller;
import com.unkanpo.model.User;
import com.unkanpo.service.imp.GameService;
import com.unkanpo.service.imp.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity home(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return new ResponseEntity<>(new User(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.checkUser(user.getUsername(), user.getPassword())) {
            modelAndView.setViewName("redirect:/users");
            session.setAttribute("userId", userService.getId(user.getUsername(), user.getPassword()));
        } else {
            modelAndView.setViewName("/user/login");
            modelAndView.addObject("error", "sai tên tài khoản hoặc mật khẩu");
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showCreateUser() {
        ModelAndView modelAndView = new ModelAndView("/user/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView createUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isNew(user.getEmail())) {
            userService.save(user);
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.setViewName("/user/register");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("error",  "gmail này đã được đăng ký");
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


}