package com.invextory.services.impl;

import com.invextory.dtos.CategoryDTO;
import com.invextory.dtos.response.Response;
import com.invextory.exceptions.NotFoundException;
import com.invextory.models.Category;
import com.invextory.repositories.CategoryRepository;
import com.invextory.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.invextory.constants.AppText.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createCategory(CategoryDTO categoryDTO) {
        log.info(LOG_CREATE_CATEGORY_INIT, categoryDTO.getName());

        Category categoryToSave = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryToSave);

        log.info(LOG_CREATE_CATEGORY_SUCCESS, categoryDTO.getName());

        return Response.builder()
                .status(200)
                .message(CATEGORY_CREATE_SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public Response getAllCategories() {
        log.info(LOG_GET_ALL_CATEGORIES_INIT);

        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        categories.forEach(category -> category.setProducts(null));

        List<CategoryDTO> categoryDTOList = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {}.getType());

        log.info(LOG_GET_ALL_CATEGORIES_SUCCESS, categoryDTOList.size());

        return Response.builder()
                .status(200)
                .message(CATEGORY_FETCH_SUCCESS_MESSAGE)
                .categories(categoryDTOList)
                .build();
    }

    @Override
    public Response getCategoryById(Long id) {
        log.info(LOG_GET_CATEGORY_BY_ID_INIT, id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LOG_CATEGORY_NOT_FOUND_BY_ID, id);
                    return new NotFoundException(ERROR_CATEGORY_NOT_FOUND);
                });

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        log.info(LOG_GET_CATEGORY_BY_ID_SUCCESS, id);

        return Response.builder()
                .status(200)
                .message(CATEGORY_FETCH_SUCCESS_MESSAGE)
                .category(categoryDTO)
                .build();
    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {
        log.info(LOG_UPDATE_CATEGORY_INIT, id);

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LOG_CATEGORY_NOT_FOUND_BY_ID, id);
                    return new NotFoundException(ERROR_CATEGORY_NOT_FOUND);
                });

        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);

        log.info(LOG_UPDATE_CATEGORY_SUCCESS, id);

        return Response.builder()
                .status(200)
                .message(CATEGORY_UPDATE_SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {
        return null;
    }

}
