package api.notificacao.controller;

import api.notificacao.dto.LoginRequestDTO;
import api.notificacao.dto.LoginResponseDTO;
import api.notificacao.dto.RegisterRequestDTO;
import api.notificacao.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) {
        return authService.login(dto);
    }

    @PostMapping("/register")
    public LoginResponseDTO register(@RequestBody @Valid RegisterRequestDTO dto) {
        return authService.register(dto);
    }
}
