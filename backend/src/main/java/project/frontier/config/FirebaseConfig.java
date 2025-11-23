package project.frontier.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void initFirebase() {
        try {
            // Use classpath resource instead of file system path
            var serviceAccountStream = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");
            
            if (serviceAccountStream == null) {
                throw new RuntimeException("Firebase service account key not found in classpath");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            System.out.println("Firebase Admin initialized successfully");

        } catch (Exception e) {
            System.err.println("Firebase initialization failed: " + e.getMessage());
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
