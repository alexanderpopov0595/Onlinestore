package com.tsystems.javaschool.onlinestore.dao.category;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.tsystems.javaschool.onlinestore.domain.category.Category;

/*
 * JPA implementation of CategorytServiceDao
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
    /*
     * JPA entity manager , provided by Spring
     */

    @PersistenceContext
    private EntityManager entityManager;

    public void addCategory(Category category) {
        entityManager.persist(category);
    }

    public void updateCategory(Category category) {
        entityManager.merge(category);

    }

    public Category selectCategory(long id) {
        return entityManager.find(Category.class, id);
    }

    public Category selectCategory(String name) {
        return (Category) entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name")
                .setParameter("name", name).getSingleResult();
    }

    public List<Category> selectCategoryList() {
        return (List<Category>) entityManager.createQuery("SELECT c FROM Category c").getResultList();
    }

    public void deleteCategory(long id) {
        entityManager.createQuery("DELETE FROM Category c WHERE c.id=:id").setParameter("id", id).executeUpdate();

    }

    public void deleteParameter(long id) {
        entityManager.createQuery("DELETE FROM Parameter p WHERE p.id=:id").setParameter("id", id).executeUpdate();
    }

    public void deleteCategoryParameterProductDetails(long id) {
        entityManager.createQuery("DELETE FROM ProductDetails pd WHERE pd.parameter.id=:id").setParameter("id", id)
                .executeUpdate();
    }

    public void deleteCategoryProducts(long id) {
        entityManager.createQuery("DELETE FROM Product p WHERE p.category.id=:id").setParameter("id", id)
                .executeUpdate();
    }

}