package hu.horvathzoltan.facade;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class AbstractFacade<T> {

    @PersistenceContext(unitName = "Park")
    protected EntityManager entityManager;
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T persist(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        
        if (violations.isEmpty()) {
            entityManager.persist(entity);
            return entity;
        } else {
           throw new RuntimeException();
        }
    }

    public T findById(Long id) {
        return entityManager.find(entityClass, id);
    }

    public void remove(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }
}
