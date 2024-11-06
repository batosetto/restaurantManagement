package ca.humber.barbara_tosetto.finalproject.services;

import ca.humber.barbara_tosetto.finalproject.models.MyUser;
import ca.humber.barbara_tosetto.finalproject.repositores.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private BCryptPasswordEncoder bcrypt;

    public UserService(UserRepository userRepo, BCryptPasswordEncoder bcrypt) {
        this.userRepo = userRepo;
        this.bcrypt = bcrypt;
    }
    //Create new user
    public int createUser(MyUser user){
        //Check if user already exists
        if (userRepo.findByUsername(user.getUsername()).isPresent()) return 0;
        //Encrypts password
        user.setPassword(bcrypt.encode(user.getPassword()));
        //Saves info
        userRepo.save(user);
        return 1;
    }
}
