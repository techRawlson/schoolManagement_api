package sampleTest.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sampleTest.demo.Services.UserService;
import sampleTest.demo.entities.User;
import sampleTest.demo.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
@Autowired
private UserService userService;
@Autowired
private UserRepository userRepository;

        //http://localhost:8081/home/users
    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("++++++++++++++++getting users check ****************");
       return userService.getUser();
    }
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllUsers() {
        try {
            // Use your repository to delete all user data
            userRepository.deleteAll();
            return ResponseEntity.ok().body("All user data deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user data: " + e.getMessage());
        }
    }

}
