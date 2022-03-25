package mk.ukim.finki.wp.wpelibrary.service;

import mk.ukim.finki.wp.wpelibrary.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username,String password,String repeatPassword,String name,String surname,String email);
}
