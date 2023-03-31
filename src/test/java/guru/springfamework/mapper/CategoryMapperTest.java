package guru.springfamework.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import junit.framework.TestCase;

import java.util.Optional;

public class CategoryMapperTest extends TestCase {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper  = CategoryMapper.INSTANCE;


    public void testCategoryToCategoryDto() {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDto(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }

    public void testCategoryDTOToCategory() {
    }
}