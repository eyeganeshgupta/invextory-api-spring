package com.invextory.services.impl;

import com.invextory.dtos.ProductDTO;
import com.invextory.dtos.response.Response;
import com.invextory.exceptions.NotFoundException;
import com.invextory.models.Category;
import com.invextory.models.Product;
import com.invextory.repositories.CategoryRepository;
import com.invextory.repositories.ProductRepository;
import com.invextory.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.invextory.constants.AppText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createProduct(ProductDTO productDTO) {
        log.info(LOG_CREATE_PRODUCT_INIT, productDTO.getName());

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ERROR_CATEGORY_NOT_FOUND));

        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(category);
        productRepository.save(product);

        log.info(LOG_CREATE_PRODUCT_SUCCESS, productDTO.getName());

        return Response.builder()
                .status(201)
                .message(PRODUCT_CREATE_SUCCESS)
                .build();
    }

    @Override
    public Response getAllProducts() {
        return null;
    }

    @Override
    public Response getProductById(Long id) {
        return null;
    }

    @Override
    public Response updateProduct(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public Response deleteProduct(Long id) {
        return null;
    }

}
