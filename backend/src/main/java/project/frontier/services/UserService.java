package project.frontier.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import project.frontier.entity.User;
import project.frontier.entity.enums.UserRole;
import project.frontier.entity.enums.UserSpecialization;
import project.frontier.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByFireBaseUid(String Uid){
        return userRepository.findByFirebaseUid(Uid);
    }
    
    public User getUserOrThrow(String Uid){
        return userRepository.findByFirebaseUid(Uid).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateRole(String uid, UserRole role){
        User user = getUserOrThrow(uid);
        user.setRole(role);
        return userRepository.save(user);
    }

    public User updateSpecialization(String uid, UserSpecialization specialization){
        User user = getUserOrThrow(uid);
        user.setSpecialization(specialization);
        return userRepository.save(user);
    }
}
