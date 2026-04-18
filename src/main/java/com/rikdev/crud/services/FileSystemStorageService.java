package com.rikdev.crud.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService{

    @Value("${media.location}")   //mapear la ruta a guardar
    private String mediaLocation;
    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() {
        try {
            rootLocation = Paths.get(mediaLocation);
            Files.createDirectories(rootLocation);
            System.out.println("Directorio creado en: " + rootLocation.toAbsolutePath());
        } catch (IOException e) {
            // Manejo del error
            System.err.println("Error al crear el directorio: " + e.getMessage());
            throw new IllegalStateException("No se pudo inicializar el directorio", e);
        }
    }

    /*GUARDAR UN ARCHIVO*/
    @Override
    public String store(MultipartFile file){
        //return null;
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String filename = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        }catch (IOException e){
            throw new RuntimeException("Failed to store file,", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename){
        try{
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("Could nor ready file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename);
        }
    }
}
