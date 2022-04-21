package mk.ukim.finki.wp.wpelibrary.service;

import mk.ukim.finki.wp.wpelibrary.model.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface AuthService {
    User login(String username, String password);

}
