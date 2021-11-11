package com.justedlev.service.filesharing.controller;

import com.justedlev.service.filesharing.service.FileSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@CrossOrigin
public class FileController {

    @Autowired
    private FileSharingService service;

    @GetMapping("/")
    public ResponseEntity<?> getFilesInfo() {
        return ResponseEntity.ok().body(service.getFilesInfo());
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) throws IOException {
        Resource resource = service.downloadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + resource.getFilename())
                .contentLength(resource.getFile().length())
                .body(resource);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) {
        service.uploadFile(file);
        return ResponseEntity.ok().build();
    }

}
