package com.cloud.Class.Management.System.Service;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {

    private final FirebaseAuth firebaseAuth;

    // SIGNUP (create user in Firebase Auth)
    public String signup(String email, String password, String firstName, String lastName, String role) {
        try {
            // Step 1: Create the user in Firebase
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(firstName + " " + lastName)
                    .setEmailVerified(false)
                    .setDisabled(false);

            UserRecord userRecord = firebaseAuth.createUser(request);
            System.out.println("Firebase user created successfully with UID: " + userRecord.getUid());

            // Step 2: Set custom claims safely
            try {
                firebaseAuth.setCustomUserClaims(userRecord.getUid(), Map.of("role", role));
                System.out.println("Custom claims set successfully for UID: " + userRecord.getUid());
            } catch (FirebaseAuthException claimEx) {
                // Catch and log detailed error if 403 occurs here
                System.err.println("Error setting custom claims for UID: " + userRecord.getUid());
                System.err.println("Firebase Error Code: " + claimEx.getAuthErrorCode());
                claimEx.printStackTrace();
                return "User created, but failed to set custom claims: " + claimEx.getMessage();
            }

            return "User created successfully with UID: " + userRecord.getUid();

        } catch (FirebaseAuthException e) {
            // Catch user creation errors
            System.err.println("Error creating Firebase user");
            System.err.println("Firebase Error Code: " + e.getAuthErrorCode());
            e.printStackTrace();
            return "Error creating user: " + e.getMessage();
        }
    }


    // VERIFY TOKEN (decode token from frontend)
    public String verifyToken(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            String role = (String) decodedToken.getClaims().get("role");

            return "Token verified! UID: " + uid + ", Email: " + email + ", Role: " + role;
        } catch (FirebaseAuthException e) {
            return "Invalid token: " + e.getMessage();
        }
    }
}
