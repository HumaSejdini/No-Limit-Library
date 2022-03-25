package mk.ukim.finki.wp.wpelibrary.service.impl;


import mk.ukim.finki.wp.wpelibrary.model.User;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidArgumentException;
import mk.ukim.finki.wp.wpelibrary.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.wp.wpelibrary.model.exception.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.wpelibrary.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.wpelibrary.repository.UserRepository;
import mk.ukim.finki.wp.wpelibrary.service.AuthService;
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

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname,String email) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email.isEmpty()) {
            throw new InvalidArgumentException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if (this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, password, name, surname,email);
        return userRepository.save(user);
    }
}
