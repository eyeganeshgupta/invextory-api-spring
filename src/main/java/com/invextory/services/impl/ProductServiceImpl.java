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
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        log.info(LOG_GET_ALL_PRODUCTS_INIT);

        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        products.forEach(p -> p.setBatches(null));
        List<ProductDTO> dtos = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
        }.getType());

        log.info(LOG_GET_ALL_PRODUCTS_SUCCESS, dtos.size());

        return Response.builder()
                .status(200)
                .message(PRODUCTS_FETCH_SUCCESS)
                .products(dtos)
                .build();
    }

    @Override
    public Response getProductById(Long id) {
        log.info(LOG_GET_PRODUCT_INIT, id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LOG_PRODUCT_NOT_FOUND, id);
                    return new NotFoundException(ERROR_PRODUCT_NOT_FOUND);
                });

        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        dto.setNumberOfBatches(product.getBatches() != null ? product.getBatches().size() : 0);

        log.info(LOG_GET_PRODUCT_SUCCESS, id);

        return Response.builder()
                .status(200)
                .message(PRODUCT_FETCH_SUCCESS)
                .product(dto)
                .build();
    }

    @Override
    public Response updateProduct(Long id, ProductDTO productDTO) {
        log.info(LOG_UPDATE_PRODUCT_INIT, id);

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LOG_PRODUCT_NOT_FOUND, id);
                    return new NotFoundException(ERROR_PRODUCT_NOT_FOUND);
                });

        modelMapper.map(productDTO, existingProduct);

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new NotFoundException(ERROR_CATEGORY_NOT_FOUND));
            existingProduct.setCategory(category);
        }

        productRepository.save(existingProduct);

        log.info(LOG_UPDATE_PRODUCT_SUCCESS, id);

        return Response.builder()
                .status(200)
                .message(PRODUCT_UPDATE_SUCCESS)
                .build();
    }

    @Override
    public Response deleteProduct(Long id) {
        return null;
    }

}
