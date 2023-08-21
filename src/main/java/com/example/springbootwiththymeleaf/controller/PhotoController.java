package com.example.springbootwiththymeleaf.controller;

import com.example.springbootwiththymeleaf.model.PhotoString;
import com.example.springbootwiththymeleaf.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class PhotoController {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        PhotoString image = photoRepository.findById(id).orElse(null);
        if (image != null && image.getBytes() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(image.getBytes(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public String listPhotos(Model model) {
        List<PhotoString> photos = photoRepository.findAll();
        model.addAttribute("photos", photos);
        return "index";
    }

    @PostMapping("/download")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
       photoRepository.save(new PhotoString());
        return "redirect:/";
    }
}
