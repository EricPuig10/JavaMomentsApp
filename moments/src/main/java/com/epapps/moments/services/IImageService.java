package com.epapps.moments.services;

import com.epapps.moments.models.Image;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    List<Image> getAllImages();
    boolean exists(Long id);
    Image findById(Long id);
    Image save(Image image);
    boolean delete(Long id);


}
