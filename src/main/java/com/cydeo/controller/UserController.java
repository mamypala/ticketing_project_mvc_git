package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model){

        // user object ${user}
        model.addAttribute("user", userService.findById(username)); // boş obje olmicak!

        // roles ${roles}
        model.addAttribute("roles", roleService.findAll());

        // users ${users}
        model.addAttribute("users", userService.findAll());

        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user){ // @ModelAttribute("user") --> yazılmasa da olur

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        userService.deleteById(username);
        return "redirect:/user/create";
    }


}
/* return e redirect dersek aşağıdaki kodları tekrar yazmaya gerek yok!
return "redirect:/user/create";
        model.addAttribute("user", new UserDTO()); // save e basınca boş yeni UserDTO object view e gelcek
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute
 */