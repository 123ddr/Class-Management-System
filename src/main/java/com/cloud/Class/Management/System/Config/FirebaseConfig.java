package com.cloud.Class.Management.System.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseConfig.class);

    @Value("${firebase.config.file}")
    private String firebaseConfigFile;

    @Value("${firebase.project-id:}")
    private String firebaseProjectId;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if (!FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.getInstance();
        }

        ClassPathResource serviceAccountResource = new ClassPathResource(firebaseConfigFile);
        if (!serviceAccountResource.exists()) {
            throw new FileNotFoundException("Firebase service account file not found in classpath: " + firebaseConfigFile);
        }

        GoogleCredentials credentials;
        try (InputStream serviceAccount = serviceAccountResource.getInputStream()) {
            credentials = GoogleCredentials.fromStream(serviceAccount);
        }

        String projectId = resolveProjectId(credentials);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        LOGGER.info("Firebase initialized successfully for project {}", projectId);
        return app;
    }

    private String resolveProjectId(GoogleCredentials credentials) {
        if (StringUtils.hasText(firebaseProjectId)) {
            return firebaseProjectId;
        }

        if (credentials instanceof ServiceAccountCredentials serviceAccountCredentials) {
            String projectId = serviceAccountCredentials.getProjectId();
            if (StringUtils.hasText(projectId)) {
                return projectId;
            }
        }

        throw new IllegalStateException("Firebase project id is not configured and could not be derived from credentials.");
    }

    @Bean
    public Firestore firestore(FirebaseApp firebaseApp) {
        return FirestoreClient.getFirestore(firebaseApp);
    }

    @Bean
    public FirebaseAuth firebaseAuth(FirebaseApp firebaseApp) {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}

