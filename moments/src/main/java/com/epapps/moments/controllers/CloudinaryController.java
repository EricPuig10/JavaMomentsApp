package com.epapps.moments.controllers;

import com.epapps.moments.dtos.Message;
import com.epapps.moments.dtos.image.ImageResDto;
import com.epapps.moments.models.Image;
import com.epapps.moments.services.ICloudinaryService;
import com.epapps.moments.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class CloudinaryController {


    ICloudinaryService cloudinaryService;

    IImageService imageService;

    @Autowired
    public CloudinaryController(ICloudinaryService cloudinaryService, IImageService imageService) {
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }




    @GetMapping("/images")
    public ResponseEntity<List<Image>> getAll(){
        List<Image> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }


    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null) {
            return new ResponseEntity(new Message("Image non valid"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Image image =
                new Image(result.get("original_filename").toString(),
                        result.get("url").toString(),
                        result.get("public_id").toString());
        imageService.save(image);
        return new ResponseEntity(new ImageResDto("Image uploaded", image.getImgUrl(), image.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        /*if(!imageService.exists(id))
            return new ResponseEntity(new Message("Doesnt exxists"), HttpStatus.NOT_FOUND);*/
        Image image = imageService.findById(id);
        Map result = cloudinaryService.delete(image.getImgId());
        imageService.delete(id);
        return new ResponseEntity(new ImageResDto("Image deleted", image.getImgUrl(), image.getId()), HttpStatus.OK);
    }


}
