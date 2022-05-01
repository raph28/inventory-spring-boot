package com.ennea.inventory.Services;

import org.springframework.stereotype.Service;

import java.io.InputStream;
@Service
public interface FileUploadService {
    int processFile(InputStream inputStream);
}
