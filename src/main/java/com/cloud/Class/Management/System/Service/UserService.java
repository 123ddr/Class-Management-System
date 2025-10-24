package com.cloud.Class.Management.System.Service;



import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService {

    // SIGNUP (create user in Firebase Auth)
    public String signup(String email, String password, String firstName, String lastName, String role) {
        try {
            // Create user in Firebase
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(firstName + " " + lastName)
                    .setEmailVerified(false)
                    .setDisabled(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            // Optional: set custom claims (role)
            FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), Map.of("role", role));

            return "User created successfully with UID: " + userRecord.getUid();
        } catch (FirebaseAuthException e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    // VERIFY TOKEN (decode token from frontend)
    public String verifyToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            String role = (String) decodedToken.getClaims().get("role");

            return "Token verified! UID: " + uid + ", Email: " + email + ", Role: " + role;
        } catch (FirebaseAuthException e) {
            return "Invalid token: " + e.getMessage();
        }
    }
}
