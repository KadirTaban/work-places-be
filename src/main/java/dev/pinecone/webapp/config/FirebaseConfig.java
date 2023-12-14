package dev.pinecone.webapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class FirebaseConfig {

    @SneakyThrows
    @Bean
    public Firestore firestore() {
        Resource resource = new ClassPathResource("./firebase-credentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
        credentials.refreshIfExpired();
        FirestoreOptions options = FirestoreOptions.newBuilder().setCredentials(credentials).build();
        return options.getService();
    }

    @SneakyThrows
    @Bean
    public Storage storage() {
        Resource resource = new ClassPathResource("./firebase-credentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
        StorageOptions options = StorageOptions.newBuilder().setCredentials(credentials).build();
        return options.getService();
    }
}