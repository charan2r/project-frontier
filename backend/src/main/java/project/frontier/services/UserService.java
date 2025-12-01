package project.frontier.services;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import project.frontier.dto.UserUpdateDTO;
import project.frontier.entity.User;
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

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
    
    public User getByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User updateUserProfile(UUID id, UserUpdateDTO updateDTO) {
        User user = getByIdOrThrow(id);
        
        // Update only non-null fields
        if (updateDTO.getFirstName() != null) {
            user.setFirstName(updateDTO.getFirstName());
        }
        if (updateDTO.getLastName() != null) {
            user.setLastName(updateDTO.getLastName());
        }
        if (updateDTO.getName() != null) {
            user.setName(updateDTO.getName());
        }
        if (updateDTO.getDateOfBirth() != null) {
            user.setDateOfBirth(updateDTO.getDateOfBirth());
        }
        if (updateDTO.getProfileImageUrl() != null) {
            user.setProfileImageUrl(updateDTO.getProfileImageUrl());
        }
        if (updateDTO.getNationality() != null) {
            user.setNationality(updateDTO.getNationality());
        }
        if (updateDTO.getGender() != null) {
            user.setGender(updateDTO.getGender());
        }
        if (updateDTO.getBloodType() != null) {
            user.setBloodType(updateDTO.getBloodType());
        }
        if (updateDTO.getRole() != null) {
            user.setRole(updateDTO.getRole());
        }
        if (updateDTO.getSpecialization() != null) {
            user.setSpecialization(updateDTO.getSpecialization());
        }
        if (updateDTO.getTotalMissionsCompleted() != null) {
            user.setTotalMissionsCompleted(updateDTO.getTotalMissionsCompleted());
        }
        if (updateDTO.getTotalExperimentsConducted() != null) {
            user.setTotalExperimentsConducted(updateDTO.getTotalExperimentsConducted());
        }       
        
        return userRepository.save(user);
    }
}
