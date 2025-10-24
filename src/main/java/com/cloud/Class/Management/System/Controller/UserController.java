package com.cloud.Class.Management.System.Controller;



import com.cloud.Class.Management.System.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam(defaultValue = "USER") String role) {
        return userService.signup(email, password, firstName, lastName, role);
    }

    // VERIFY TOKEN (check token from frontend)
    @PostMapping("/verify")
    public String verifyToken(@RequestParam String idToken) {
        return userService.verifyToken(idToken);
    }
}
