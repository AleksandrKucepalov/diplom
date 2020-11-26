package main.controller;

import main.api.request.*;
import main.api.response.ResultLoginResponse;
import main.api.response.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    //15
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {

        return new ResponseEntity<>(new ResultLoginResponse(), HttpStatus.OK);
    }

    //16
    @GetMapping("/check")
    public ResponseEntity check() {

        return new ResponseEntity<>(new ResultLoginResponse(), HttpStatus.OK);
    }

    //17
    @PostMapping("/restore")
    public ResponseEntity restore(@RequestBody RestoreRequest restoreRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //18
    @PostMapping("/password")
    public ResponseEntity password(@RequestBody PasswordRequest passwordRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //19
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(new ResultResponse(), HttpStatus.OK);
    }

    //21
    @GetMapping("/captcha")
    public ResponseEntity captcha() {

        return new ResponseEntity<>(new ResultLoginResponse(), HttpStatus.OK);
    }

    //26
    @GetMapping("/logout")
    public ResponseEntity logout() {

        return new ResponseEntity<>(new ResultLoginResponse(), HttpStatus.OK);
    }
}
