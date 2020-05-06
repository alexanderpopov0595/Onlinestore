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

    /**
     * Method adds category to database
     * @param category
     */
    public void addCategory(Category category) {
        entityManager.persist(category);
    }

    /**
     * Method updates category in database
     * @param category
     */
    public void updateCategory(Category category) {
        entityManager.merge(category);

    }

    /**
     * Methods returns category by id
     * @param id
     * @return
     */
    public Category selectCategory(long id) {
        return entityManager.find(Category.class, id);
    }

    /**
     * Methods returns category by category name
     * @param name
     * @return category
     */
    public Category selectCategory(String name) {
        return (Category) entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }

    /**
     *  Method returns category list
     * @return category list
     */
    public List<Category> selectCategoryList() {
        return  entityManager.createQuery("SELECT c FROM Category c")
                .getResultList();
    }

    /**
     * Method deletes category by id
     * @param id
     */
    public void deleteCategory(long id) {
        entityManager.createQuery("UPDATE  Category c  SET c.status=:status WHERE c.id=:id")
                .setParameter("status", Status.DELETED)
                .setParameter("id", id).executeUpdate();

    }

    /**
     * Method deletes category parameter
     * @param id
     */
    public void deleteParameter(long id) {
        entityManager.createQuery("DELETE FROM Parameter p WHERE p.id=:id").setParameter("id", id).executeUpdate();
    }

    /**
     * Method delete category parameter product details
     * @param id
     */
    public void deleteCategoryParameterProductDetails(long id) {
        entityManager.createQuery("DELETE FROM ProductDetails pd WHERE pd.parameter.id=:id").setParameter("id", id)
                .executeUpdate();

    }

    /**
     * Method deletes category products
     * @param id
     */
    public void deleteCategoryProducts(long id) {
        entityManager.createQuery("UPDATE Product p SET p.quantity=:quantity WHERE p.category.id=:id")
                .setParameter("quantity", 0)
                .setParameter("id", id)
                .executeUpdate();
    }

}