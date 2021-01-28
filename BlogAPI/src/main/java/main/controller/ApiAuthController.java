package main.controller;


import main.api.request.LoginRequest;
import main.api.request.PasswordRequest;
import main.api.request.RegisterRequest;
import main.api.request.RestoreRequest;
import main.api.response.ResultResponse;
import main.repository.UserRepository;
import main.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @Autowired
    public ApiAuthController(AuthenticationManager authenticationManager, UserRepository userRepository, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }


    //15
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            Authentication auth = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getEmail(),
                                    loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            User user = (User) auth.getPrincipal();
            String email = user.getUsername();
            return new ResponseEntity<>(authService.getAuthResponse(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
        }


    }

    //16
    @GetMapping("/check")
    public ResponseEntity check(Principal principal) {

        if (principal == null) {
            return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
        } else {
            String email = principal.getName();
            return new ResponseEntity<>(authService.getAuthResponse(email), HttpStatus.OK);
        }
    }

    //17
    @PostMapping("/restore")
    public ResponseEntity restore(@RequestBody RestoreRequest restoreRequest) {

        return new ResponseEntity<>(authService.getRestoreResponse(restoreRequest.getEmail()), HttpStatus.OK);
    }

    //18
    @PostMapping("/password")
    public ResponseEntity password(@RequestBody PasswordRequest passwordRequest) {

        return new ResponseEntity<>(authService.getPasswordResponse(passwordRequest), HttpStatus.OK);
    }

    //19
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(authService.getRegisterResponse(registerRequest), HttpStatus.OK);
    }

    //21
    @GetMapping("/captcha")
    public ResponseEntity captcha() {


        return new ResponseEntity<>(authService.captcha(), HttpStatus.OK);
    }

    //26
    @GetMapping("/logout")
    public ResponseEntity logout() {
        ResultResponse response = new ResultResponse();
        response.setResult(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
