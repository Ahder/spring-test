package com.rizomm.m2.exam.business.services.impl;

import com.rizomm.m2.exam.business.entities.Picture;
import com.rizomm.m2.exam.business.repositories.PictureRepository;
import com.rizomm.m2.exam.business.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Override
    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findById(Long picId)  {
        return pictureRepository.findById(picId);
    }

    @Override
    public Picture createPicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Optional<Picture> deletePicture(Long picId)  {
        Optional<Picture> picture = pictureRepository.findById(picId);
        if (picture.isPresent()) {
            pictureRepository.delete(picture.get());
        }
        return picture;
    }
    @Override
    public Optional<Picture> updatePicture(Picture picture) {
        Optional<Picture> existingPicture = pictureRepository.findById(picture.getId());
        if (existingPicture.isPresent()) {
            pictureRepository.save(existingPicture.get());
        }
        return existingPicture;
    }
}
