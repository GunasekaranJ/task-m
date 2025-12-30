package Backendprojects.Project1.Controller;

import Backendprojects.Project1.models.User;
import Backendprojects.Project1.Repository.UserRepository;
import Backendprojects.Project1.Service.UserService;
import Backendprojects.Project1.utils.Jwtutils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final Jwtutils jwtutils;



    @PostMapping("/register")
    public ResponseEntity<?> UserRegister(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));

        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<String>("Email Already Exists", HttpStatus.CONFLICT);
        }

        userService.createUser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("Registered Successfully", HttpStatus.OK);


    }

    @PostMapping("/login")
    public ResponseEntity<?> UserLogin(@RequestBody Map<String,String> body){
       String email = body.get("email");
       String password = body.get("password");

       var userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not Registered",HttpStatus.UNAUTHORIZED);
        }

        User user = userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
           return new ResponseEntity<>("Wrong Password",HttpStatus.UNAUTHORIZED);
        }

        String token = jwtutils.generateToken(user.getEmail());
        return new ResponseEntity<>(Map.of("token",token),HttpStatus.OK);

    }
}
