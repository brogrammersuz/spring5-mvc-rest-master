package guru.springfamework.service.impl;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.mapper.CategoryMapper;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDto)
                .toList();
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::categoryToCategoryDto)
                .orElse(null);
    }
}
