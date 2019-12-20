package com.rizomm.m2.exam.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizomm.m2.exam.business.entities.Product;
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
public class ProductControllerTest {

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

        Product fakeProduct1 = Product.builder().id(1L).superModel("FakeSuperModel1").build();
        Product fakeProduct2 = Product.builder().id(2L).superModel("FakeSuperMode2").build();


        when(productService.findById(1L)).thenReturn(Optional.of(fakeProduct1));
        when(productService.findById(2L)).thenReturn(Optional.of(fakeProduct2));
    }

    @Test
    public void whenProductExistThenReturnIt() throws Exception {
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }


    @Test
    public void whenProductDoesNotExistThenReturnNotFoundStatus() throws Exception {
        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound());
    }

    // TODO: check list size
    @Test
    public void whenTwoProductsThenReturnListNotEmpty() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(2)));
    }

    @Test
    public void whenPostProductThenReturnNewProductAndStatusCreated() throws Exception {

        Product fakeProductToPost = Product.builder().superModel("fakeProductToPost").build();

        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fakeProductToPost)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)));
    }

    @Test
    public void whenDeleteNotExistingProductThenReturnNotFound() throws Exception {
        mockMvc.perform(delete("/products/99"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void whenDeleteExistingProductThenReturnOk() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void whenEditExistingProductThenReturnOk() throws Exception {
        Product fakeProductToPost = Product.builder().id(1L).superModel("fakeProductToPost").build();

        mockMvc.perform(
                put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fakeProductToPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.superModel", is("fakeProductToPost")));
    }

    @Test
    public void whenEditNonExistingProductThenReturnNotFound() throws Exception {
        Product fakeProductToPost = Product.builder().id(99L).superModel("fakeProductToPost").build();

        mockMvc.perform(
                put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(fakeProductToPost)))
                .andExpect(status().isNotFound());
    }

}
