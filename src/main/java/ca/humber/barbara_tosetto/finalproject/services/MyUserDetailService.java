package ca.humber.barbara_tosetto.finalproject.services;

import ca.humber.barbara_tosetto.finalproject.models.MyUser;
import ca.humber.barbara_tosetto.finalproject.repositores.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UserRepository userRepo;

    public MyUserDetailService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> userOp = userRepo.findByUsername(username);

        if (userOp.isPresent()){
            MyUser user = userOp.get();
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        } else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
