package dev.pinecone.webapp.fileservice;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final Storage storage;

    @SneakyThrows
    public String saveFile(MultipartFile file){
        String fileName = UUID.randomUUID().toString();

        String bucketName = "work-places-be.appspot.com";
        BlobId blobId = BlobId.of(bucketName, fileName);

        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}
