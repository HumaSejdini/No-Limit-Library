package mk.ukim.finki.wp.wpelibrary.web;

import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidArgumentException;
import mk.ukim.finki.wp.wpelibrary.model.exception.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.wpelibrary.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;


    public RegisterController(AuthService authService){
        this.authService=authService;
    }
    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        return "register.html";
    }

    @PostMapping
    public  String register(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String repeatPassword,
                            @RequestParam String name,
                            @RequestParam String surname,
                            @RequestParam String email){
        try{
            this.authService.register(username, password, repeatPassword, name, surname,email);
            return "redirect:/login";
        }catch(PasswordsDoNotMatchException | InvalidArgumentException exception){
            return "redirect:/register?error="+exception.getMessage();
        }

    }


}
