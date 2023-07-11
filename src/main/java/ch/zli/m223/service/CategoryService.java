package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }
    
    @Transactional
    public String deleteCategory(Category category) {
        var query = entityManager.createQuery("FROM Category WHERE id=" + String.valueOf(category.getCategoryId()));
        entityManager.remove(query.getSingleResult());
        return "" + category.getCategoryId() + " deleted!";
    }

    @Transactional
    public Category editCategory(Long id, Category updatedCategory, Category category) {
        updatedCategory = findById(id);
        updatedCategory.setCategoryTitle(category.getCategoryTitle());
        entityManager.merge(updatedCategory);
        return updatedCategory;
    }
    
    @Transactional
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }    
}
