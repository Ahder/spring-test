package com.rizomm.m2.exam.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizomm.m2.exam.business.entities.Picture;
import com.rizomm.m2.exam.business.repositories.PictureRepository;
import com.rizomm.m2.exam.business.repositories.ProductRepository;
import com.rizomm.m2.exam.business.services.PictureService;
import com.rizomm.m2.exam.business.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PictureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private PictureService pictureService;

    @MockBean
    private PictureRepository pictureRepository;

    @Before
    public void init() {

        Picture fakePicture1 = Picture.builder().id(1L).build();
        Picture fakePicture2 = Picture.builder().id(2L).build();


        when(pictureService.findById(1L)).thenReturn(Optional.of(fakePicture1));
        when(pictureService.findById(2L)).thenReturn(Optional.of(fakePicture2));
    }

    @Test
    public void whenPictureExistThenReturnIt() throws Exception {
        mockMvc.perform(get("/pictures/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }


    @Test
    public void whenPictureDoesNotExistThenReturnNotFoundStatus() throws Exception {
        mockMvc.perform(get("/pictures/99"))
                .andExpect(status().isNotFound());
    }

    // TODO: check list size
    @Test
    public void whenTwoPicturesThenReturnListNotEmpty() throws Exception {
        mockMvc.perform(get("/pictures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pictures", hasSize(2)));
    }

    @Test
    public void whenPostPictureThenReturnNewPictureAndStatusCreated() throws Exception {

        Picture fakePictureToPost = Picture.builder().build();

        mockMvc.perform(
                post("/pictures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fakePictureToPost)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)));
    }

    @Test
    public void whenDeleteNotExistingPictureThenReturnNotFound() throws Exception {
        mockMvc.perform(delete("/pictures/99"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void whenDeleteExistingPictureThenReturnOk() throws Exception {
        mockMvc.perform(delete("/pictures/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void whenEditExistingPictureThenReturnOk() throws Exception {
        Picture fakePictureToPost = Picture.builder().id(1L).build();

        mockMvc.perform(
                put("/pictures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fakePictureToPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superModel", is("fakePictureToPost")));
    }

    @Test
    public void whenEditNonExistingPictureThenReturnNotFound() throws Exception {
        Picture fakePictureToPost = Picture.builder().id(99L).build();

        mockMvc.perform(
                put("/pictures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fakePictureToPost)))
                .andExpect(status().isNotFound());
    }

}
