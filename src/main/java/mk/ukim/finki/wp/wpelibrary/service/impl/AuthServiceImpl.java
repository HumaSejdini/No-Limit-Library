package mk.ukim.finki.wp.wpelibrary.service.impl;


import mk.ukim.finki.wp.wpelibrary.model.User;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidArgumentException;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.wp.wpelibrary.model.exception.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.wpelibrary.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.wpelibrary.repository.UserRepository;
import mk.ukim.finki.wp.wpelibrary.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
