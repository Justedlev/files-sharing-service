package com.justedlev.service.filesharing.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justedlev.service.filesharing.storage.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Storage {

    private final Path root = Paths.get("./data");

    @Autowired
    private ObjectMapper mapper;

    public List<FileInfo> getFilesInfo() {
        File[] files = root.toFile().listFiles();
        if (files == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(files)
                .map(f -> FileInfo.builder()
                        .size(f.length())
                        .name(f.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public void saveFile(MultipartFile file) {
        try {
            Path copyLocation = Paths
                    .get(root + File.separator + StringUtils.cleanPath(
                            Objects.requireNonNull(file.getOriginalFilename())));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resource getFile(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()) {
                return resource;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
