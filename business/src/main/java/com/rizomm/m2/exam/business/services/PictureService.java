package com.rizomm.m2.exam.business.services;


import com.rizomm.m2.exam.business.entities.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    List<Picture> findAll();

    Optional<Picture> findById(Long prodId);

    Picture createPicture(Picture product);

    Optional<Picture> deletePicture(Long prodId);

    Optional<Picture> updatePicture(Picture product);
}
