package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.DTO.UserRequestDTO;
import com.cloud.Class.Management.System.DTO.UserResponseDTO;
import com.cloud.Class.Management.System.Entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Firestore firestore = FirestoreClient.getFirestore();

    // Create User
    public UserResponseDTO createUser(UserRequestDTO dto) throws ExecutionException, InterruptedException {
        User user = User.builder()
                .role(dto.getRole())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .isActive(dto.isActive())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        DocumentReference ref = firestore.collection("users").document();
        user.setUserId(ref.getId());
        ref.set(user).get();

        return mapToResponse(user);
    }

    // Get all users
    public List<UserResponseDTO> getAllUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = firestore.collection("users").get();
        return query.get().getDocuments().stream()
                .map(doc -> doc.toObject(User.class))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get user by ID
    public UserResponseDTO getUserById(String userId) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = firestore.collection("users").document(userId).get().get();
        if (!snapshot.exists()) throw new RuntimeException("User not found");
        return mapToResponse(snapshot.toObject(User.class));
    }

    // Update user
    public UserResponseDTO updateUser(String userId, UserRequestDTO dto) throws ExecutionException, InterruptedException {
        DocumentReference ref = firestore.collection("users").document(userId);
        DocumentSnapshot snapshot = ref.get().get();
        if (!snapshot.exists()) throw new RuntimeException("User not found");

        User user = snapshot.toObject(User.class);
        user.setRole(dto.getRole());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setActive(dto.isActive());
        user.setUpdatedAt(LocalDateTime.now());

        ref.set(user).get();
        return mapToResponse(user);
    }

    // Delete user
    public void deleteUser(String userId) throws ExecutionException, InterruptedException {
        firestore.collection("users").document(userId).delete().get();
    }

    private UserResponseDTO mapToResponse(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setRole(user.getRole());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
