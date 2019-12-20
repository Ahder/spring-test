package com.rizomm.m2.exam.business.converters;

import com.rizomm.m2.exam.business.dto.PictureDto;
import com.rizomm.m2.exam.business.entities.Picture;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PictureConverter {

    private ModelMapper modelMapper;

    public PictureConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PictureDto convert(Picture picture) {
        return modelMapper.map(picture, PictureDto.class);
    }

    public Picture convert(PictureDto pictureDto) {
        return modelMapper.map(pictureDto, Picture.class);
    }
}
