package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller

public class UserController {


    //todo Сделать аутентификация и регистрацию пользователей

//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/login")
//    public String login(Principal principal, Model model) {
//        model.addAttribute("user", userService.getUserByPrincipal(principal));
//        return "login";
//    }
//
//    @GetMapping("/registration")
//    public String registration(Principal principal, Model model) {
//        model.addAttribute("user", userService.getUserByPrincipal(principal));
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String createUser(User user, Model model) {
//        if (!userService.createUser(user)) {
//            model.addAttribute("errorMessage", "Пользователь с почтой: " + user.getName() + " уже существует.");
//            return "registration";
//        }
//        return "redirect:/login";
//    }
}
