package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.converters.PictureConverter;
import com.rizomm.m2.exam.business.converters.PictureConverter;
import com.rizomm.m2.exam.business.dto.PictureDto;
import com.rizomm.m2.exam.business.dto.PictureDto;
import com.rizomm.m2.exam.business.entities.Picture;
import com.rizomm.m2.exam.business.entities.Picture;
import com.rizomm.m2.exam.business.services.PictureService;
import com.rizomm.m2.exam.business.services.PictureService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    private PictureService pictureService;
    private PictureConverter pictureConverter;

    public PictureController(
            PictureService pictureService,
            PictureConverter pictureConverter) {
        this.pictureService = pictureService;
        this.pictureConverter = pictureConverter;
    }


    @GetMapping
    public ResponseEntity<List<PictureDto>> getAllPictures() {
        List<Picture> pictures = pictureService.findAll();
        List<PictureDto> pictureDtos = new ArrayList<>();

        for (Picture picture: pictures) {
            pictureDtos.add(pictureConverter.convert(picture));
        }
        return ResponseEntity.ok(pictureDtos);
    }

    @GetMapping("{prodId}")
    public ResponseEntity<PictureDto> getPictureById(@PathVariable Long prodId) {

        Optional<Picture> picture = pictureService.findById(prodId);
        if(picture.isPresent()) {
            return ResponseEntity.ok(pictureConverter.convert(picture.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PictureDto> AddPicture(@RequestBody PictureDto pictureDto) {
        return new ResponseEntity<>(pictureConverter.convert(pictureService.createPicture(pictureConverter.convert(pictureDto))), HttpStatus.CREATED);
    }

    @DeleteMapping("{prodId}")
    public ResponseEntity deletePicture(@PathVariable Long prodId)  {
        Optional<Picture> picture = pictureService.deletePicture(prodId);
        if(picture.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<PictureDto> editPicture(@RequestBody PictureDto pictureDto) {
        Optional<Picture> picture = pictureService.updatePicture(pictureConverter.convert(pictureDto));
        if(picture.isPresent()) {
            return ResponseEntity.ok(pictureConverter.convert(picture.get()));
        }
        return ResponseEntity.notFound().build();
    }


}
