package com.pedromonteiro.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    
    @Test
    void testNewCategory() {
        Assertions.assertNotNull(new Category());
    }
}
