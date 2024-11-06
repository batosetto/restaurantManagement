package ca.humber.barbara_tosetto.finalproject.repositores;

import ca.humber.barbara_tosetto.finalproject.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    public Optional<MyUser> findByUsername(String username);

}
