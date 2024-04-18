package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){

        model.addAttribute("user", new UserDTO());

        model.addAttribute("roles", roleService.findAll()); // role leri db den seçebilmek için

        model.addAttribute("users", userService.findAll()); // tablo ya db den veri çekmek için

        return "/user/create";
    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user){

        userService.save(user);

        return "redirect:/user/create";

    }
}
/* return e redirect dersek aşağıdaki kodları tekrar yazmaya gerek yok!
return "redirect:/user/create";
        model.addAttribute("user", new UserDTO()); // save e basınca boş yeni UserDTO object view e gelcek
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());

 */