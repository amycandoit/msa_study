package com.example.store.store.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class FirebaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    public List<String> uploadFiles(List<MultipartFile> multipartFiles, List<String> nameFiles, String placeId) throws IOException, FirebaseAuthException {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        List<String> mediaLinks = new ArrayList<>();

        for (int i = 0; i < multipartFiles.size(); i++) {
            MultipartFile multipartFile = multipartFiles.get(i);
            String nameFile = nameFiles.get(i);
            InputStream content = new ByteArrayInputStream(multipartFile.getBytes());
            String fullPath = placeId + "/" + nameFile; // Construct the desired path
            Blob blob = bucket.create(fullPath, content, multipartFile.getContentType());
            mediaLinks.add(blob.getMediaLink());
        }

        return mediaLinks;
    }
}
