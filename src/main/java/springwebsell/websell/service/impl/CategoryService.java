package springwebsell.websell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springwebsell.websell.model.Category;
import springwebsell.websell.repository.CategoryRepository;
import springwebsell.websell.service.ICategoryService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        Category categorySave = new Category(category.getName());
        return categoryRepository.save(categorySave);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdate = categoryRepository.findById(category.getId()).get();
        categoryUpdate.setName(category.getName());
        categoryUpdate.set_delete(category.is_delete());
        categoryUpdate.set_activated(category.is_activated());
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.getById(id);
        category.set_delete(true);
        category.set_activated(false);
        categoryRepository.save(category);
    }

    @Override
    public void enableById(Long id) {
        Category category = categoryRepository.getById(id);
        category.set_activated(true);
        category.set_delete(false);
        categoryRepository.save(category);
    }
}
