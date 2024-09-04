package com.jbohorquez.emazon_hexagonal.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    private Article article;
    private Set<Category> categories;

    @BeforeEach
    public void setUp() {
        Brand brand = new Brand(1L, "Brand Name");
        categories = new HashSet<>();
        categories.add(new Category(1L, "Category 1", "Description 1"));
        article = new Article(1L, "Article 1", "Description 1", 10, 99.99, brand, categories);
    }

    @Test
    public void testArticleCreation() {
        assertNotNull(article);
        assertEquals(1L, article.getId());
        assertEquals("Article 1", article.getName());
        assertEquals("Description 1", article.getDescription());
        assertEquals(10, article.getStock());
        assertEquals(99.99, article.getPrice());
        assertEquals(categories, article.getCategories());
    }

    @Test
    public void testSettersAndGetters() {
        article.setId(2L);
        assertEquals(2L, article.getId());

        article.setName("Updated Article");
        assertEquals("Updated Article", article.getName());

        article.setDescription("Updated Description");
        assertEquals("Updated Description", article.getDescription());

        article.setStock(20);
        assertEquals(20, article.getStock());

        article.setPrice(199.99);
        assertEquals(199.99, article.getPrice());

        Set<Category> newCategories = new HashSet<>();
        newCategories.add(new Category(2L, "Category 2", "Description 2"));
        article.setCategories(newCategories);
        assertEquals(newCategories, article.getCategories());
    }

    @Test
    void testToString() {
        String expectedString = "Article{" +
                "id=1" +
                ", name='Article 1'" +
                ", description='Description 1'" +
                ", stock=10" +
                ", price=99.99" +
                ", brand=Brand{" +
                "id=1" +
                ", name='Brand Name'" +
                ", description='null'" +
                '}' +
                ", categories=[Category{" +
                "id=1" +
                ", name='Category 1'" +
                ", description='Description 1'" +
                "}]" +
                '}';

        String actualString = article.toString();
        System.out.println("Expected: " + expectedString);
        System.out.println("Actual: " + actualString);

    }
}