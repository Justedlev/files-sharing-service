package com.justedlev.service.filesharing.service;

import com.justedlev.service.filesharing.service.interfaces.IFileSharing;
import com.justedlev.service.filesharing.storage.Storage;
import com.justedlev.service.filesharing.storage.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileSharingService implements IFileSharing {

    @Autowired
    private Storage storage;

    @Override
    public List<FileInfo> getFilesInfo() {
        return storage.getFilesInfo();
    }

    @Override
    public void uploadFile(MultipartFile file) {
        storage.saveFile(file);
    }

    @Override
    public Resource downloadFile(String filename) {
        return storage.getFile(filename);
    }

}
