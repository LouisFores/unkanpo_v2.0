package com.unkanpo.controller.api;
import com.unkanpo.dto.AlertDTO;
import com.unkanpo.dto.RechargeDTO;
import com.unkanpo.dto.AlertStatus;
import com.unkanpo.dto.UserLoginDTO;
import com.unkanpo.model.GameAccount;
import com.unkanpo.model.User;
import com.unkanpo.service.imp.AccountService;
import com.unkanpo.service.imp.GameService;
import com.unkanpo.service.imp.RentalHistoryService;
import com.unkanpo.service.imp.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class  UserApi {
    @Autowired
    private GameService gameService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private RentalHistoryService rentalService;


    @GetMapping
    public ResponseEntity home(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/formLogin")
    public ModelAndView showLoginUser() {
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginForm() {
        return new ResponseEntity<>(new User(), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDTO user, HttpSession session) {
        try {
            session.setAttribute("userId", userService.Login(user.getUsername(), user.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new LoginResponse("success"), HttpStatus.OK);
    }

    class LoginResponse {
        public String msg;
        public String code;
        public LoginResponse(String msg) {
            this.msg = msg;
        }
    }

    @GetMapping("/register")
    public ModelAndView showCreateUser() {
        ModelAndView modelAndView = new ModelAndView("/user/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            if (userService.isNew(user.getEmail(), user.getUsername())) {
                userService.save(user);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(gameService.findAll(), HttpStatus.OK);
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

    @PutMapping("/{id}")
    public String updateUser(@RequestBody User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PutMapping("/{id}/coins")
    public ResponseEntity rechargeCoin(@RequestBody RechargeDTO coin,@PathVariable("id") Long userId) {
        try {
            userService.rechargeCoin(userId, coin.getCoinRecharge());
        } catch (Exception e) {
            return new ResponseEntity<>(new AlertDTO(AlertStatus.Error,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AlertDTO(AlertStatus.Good,"Nạp tiền thành công"),HttpStatus.OK);
    }

    @PostMapping("/{id}/accounts/{accountId}")
    public ResponseEntity rentalAccount(@PathVariable("id") Long id,@PathVariable("accountId") Long accountId) {
        User user = userService.findById(id).get();
        GameAccount account = accountService.findById(accountId).get();
        try {
            rentalService.startRent(user,account);
        } catch (Exception e) {
            return new ResponseEntity<>(new AlertDTO(AlertStatus.Error,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AlertDTO(AlertStatus.Good,"Thuê thành công!"),HttpStatus.OK);
    }
}