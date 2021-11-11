package com.justedlev.service.filesharing.service.interfaces;

import com.justedlev.service.filesharing.storage.model.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileSharing {

    List<FileInfo> getFilesInfo();

    void uploadFile(MultipartFile file);

    Resource downloadFile(String filename);

}
