package com.pedromonteiro.infrastructure.category;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pedromonteiro.infrastructure.MySQLGatewayTest;
import com.pedromonteiro.infrastructure.category.persistence.CategoryRepository;


@MySQLGatewayTest
public class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryGateway;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInjectedDependencies() {
        assertNotNull(categoryGateway);
        assertNotNull(categoryRepository);
   
    }

    
}