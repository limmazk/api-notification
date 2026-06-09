package api.notificacao.controller;

import api.notificacao.entity.UserModel;
import api.notificacao.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserModel> getAllUser(){
        return userService.findAll();
    }

    @PostMapping("")
    public UserModel createUser(@RequestBody UserModel userModel){
        return userService.create(userModel);
    }
}
