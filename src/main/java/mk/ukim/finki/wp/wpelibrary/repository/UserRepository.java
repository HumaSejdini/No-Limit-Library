package mk.ukim.finki.wp.wpelibrary.repository;

import mk.ukim.finki.wp.wpelibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
