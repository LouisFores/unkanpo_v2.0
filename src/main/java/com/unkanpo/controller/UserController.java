package com.unkanpo.controller;
import com.unkanpo.dto.AlertDTO;
import com.unkanpo.dto.AlertStatus;
import com.unkanpo.model.User;
import com.unkanpo.service.imp.GameService;
import com.unkanpo.service.imp.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            modelAndView.setViewName("/user/login");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("alert", new AlertDTO(AlertStatus.Good,"Tạo tài khoản thành công!"));

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


}