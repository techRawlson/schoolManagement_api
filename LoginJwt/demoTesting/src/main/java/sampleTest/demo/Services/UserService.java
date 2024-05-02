package sampleTest.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sampleTest.demo.entities.User;
import sampleTest.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store=new ArrayList<>();
//    public UserService(){
//        store.add(new User(UUID.randomUUID().toString(),"aman1@gmail.com","aman1"));
//        store.add(new User(UUID.randomUUID().toString(),"aman2@gmail.com","aman2"));
//        store.add(new User(UUID.randomUUID().toString(),"aman3@gmail.com","aman3"));
//        store.add(new User(UUID.randomUUID().toString(),"aman4@gmail.com","aman4"));
//    }
@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;
    public List<User> getUser(){
        return userRepository.findAll();
    }
    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }




}
