package com.rikdev.crud.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();
    String store(MultipartFile file);         //almacenar archivo
    Resource loadAsResource(String filename);     //cargar archivo
}
