package mk.ukim.finki.wp.wpelibrary.service;

import mk.ukim.finki.wp.wpelibrary.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import mk.ukim.finki.wp.wpelibrary.model.enumerations.Role;


public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname,String email, Role role);
}
