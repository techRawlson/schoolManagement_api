package sampleTest.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import sampleTest.demo.entities.User;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public Optional<User> findByEmail(String email);

}
