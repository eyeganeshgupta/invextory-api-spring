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
        return null;
    }

    @Override
    public Response getCategoryById(Long id) {
        return null;
    }

    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public Response deleteCategory(Long id) {
        return null;
    }

}
