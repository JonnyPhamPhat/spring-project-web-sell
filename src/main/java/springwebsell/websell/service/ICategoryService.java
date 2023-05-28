package springwebsell.websell.service;

import springwebsell.websell.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void delete(Long id);
    void enableById(Long id);
}
