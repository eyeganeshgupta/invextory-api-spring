package com.invextory.services;

import com.invextory.dtos.ProductDTO;
import com.invextory.dtos.response.Response;

public interface ProductService {

    Response createProduct(ProductDTO productDTO);

    Response getAllProducts();

    Response getProductById(Long id);

    Response updateProduct(Long id, ProductDTO productDTO);

    Response deleteProduct(Long id);

}
