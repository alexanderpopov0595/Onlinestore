package com.tsystems.javaschool.onlinestore.dao.category;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tsystems.javaschool.onlinestore.enums.Status;
import org.springframework.stereotype.Repository;
import com.tsystems.javaschool.onlinestore.domain.category.Category;

/**
 * JPA implementation of Category dao
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    /**
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
        return (Category) entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name AND c.status=:status")
                .setParameter("name", name)
                .setParameter("status", Status.DELETED)
                .getSingleResult();
    }

    public List<Category> selectCategoryList() {
        return  entityManager.createQuery("SELECT c FROM Category c WHERE c.status=:status")
                .setParameter("status", Status.ACTIVE)
                .getResultList();
    }

    public void deleteCategory(long id) {
        entityManager.createQuery("UPDATE  Category c  SET c.status=:status WHERE c.id=:id")
                .setParameter("status", Status.DELETED)
                .setParameter("id", id).executeUpdate();

    }

    public void deleteParameter(long id) {
        entityManager.createQuery("DELETE FROM Parameter p WHERE p.id=:id").setParameter("id", id).executeUpdate();
    }

    public void deleteCategoryParameterProductDetails(long id) {
        entityManager.createQuery("DELETE FROM ProductDetails pd WHERE pd.parameter.id=:id").setParameter("id", id)
                .executeUpdate();

    }

    public void deleteCategoryProducts(long id) {
        entityManager.createQuery("UPDATE Product p SET p.quantity=:quantity WHERE p.category.id=:id")
                .setParameter("quantity", 0)
                .setParameter("id", id)
                .executeUpdate();
    }

}