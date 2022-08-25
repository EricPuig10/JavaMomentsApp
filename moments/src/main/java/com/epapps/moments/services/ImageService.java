package com.epapps.moments.services;

import com.epapps.moments.exceptions.NotFoundException;
import com.epapps.moments.models.Image;
import com.epapps.moments.repositories.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageService implements IImageService {
    @Autowired
    IImageRepository imageRepository;
    ICloudinaryService cloudinaryService;



    @Override
    public List<Image> getAllImages (){
        return imageRepository.findByOrderById();
    }
    @Override
    public Image findById (Long id) {
        return imageRepository.findById(id).get();
    }
    @Override
    public Image save (Image image){
        return imageRepository.save(image);
    }
    @Override
    public boolean delete (Long id) {
        var image = imageRepository.findById(id);
        if(image.isEmpty()) throw new NotFoundException("Image Not Found", "P-105");
        imageRepository.delete(image.get());
        return true;

    }
    @Override
    public boolean exists (Long id) {
        return imageRepository.existsById(id);
    }



}
