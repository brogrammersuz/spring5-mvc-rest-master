package guru.springfamework.service.impl;

import guru.springfamework.domain.Category;
import guru.springfamework.mapper.CategoryMapper;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.service.CategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    private static final String NAME = "Something";

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() {
        List<Category> categoryList = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categoryList);

        var result = categoryService.getAllCategories();

        assertEquals(3, result.size());
    }

    @Test
    public void getByName(){
        Category category = new Category();
        category.setId(1L);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        var dto = categoryService.getCategoryByName(NAME);

        assertEquals(Long.valueOf(1L), dto.getId());
        assertEquals(NAME, dto.getName());
    }


}