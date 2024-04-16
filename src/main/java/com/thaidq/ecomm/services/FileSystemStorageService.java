package com.thaidq.ecomm.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
@Service
public class FileSystemStorageService {
    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = Paths.get("D:\\Spring_Boot\\Project\\ecomm\\src\\main\\resources\\static\\uploads");
    }

    public String store(MultipartFile file) throws IOException {
        Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename()))).normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return file.getOriginalFilename(); // Return the filename
    }


    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
