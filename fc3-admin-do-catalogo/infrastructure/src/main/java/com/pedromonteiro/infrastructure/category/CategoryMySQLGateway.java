package com.pedromonteiro.infrastructure.category;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pedromonteiro.domain.category.Category;
import com.pedromonteiro.domain.category.CategoryGateway;
import com.pedromonteiro.domain.category.CategoryID;
import com.pedromonteiro.domain.pagination.CategorySearchQuery;
import com.pedromonteiro.domain.pagination.Pagination;
import com.pedromonteiro.infrastructure.category.persistence.CategoryJpaEntity;
import com.pedromonteiro.infrastructure.category.persistence.CategoryRepository;

@Service
public class CategoryMySQLGateway implements CategoryGateway{

    private final CategoryRepository repository;

    public CategoryMySQLGateway(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(final Category aCategory) {
       return save(aCategory);
    }

    @Override
    public void deleteById(CategoryID anId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Category update(Category aCategory) {
        return save(aCategory);
    }

    @Override
    public Pagination<Category> findAll(CategorySearchQuery aQuery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    private Category save(final Category aCategory) {
        return this.repository.save(CategoryJpaEntity.from(aCategory)).toAggregate();
    }
    
    
    
}
