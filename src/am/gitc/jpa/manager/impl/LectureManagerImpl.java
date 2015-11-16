package am.gitc.jpa.manager.impl;

import am.gitc.jpa.data.Lecture;
import am.gitc.jpa.manager.ILectureManager;
import am.gitc.jpa.manager.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Zorik Zaqaryan on 07.11.2015.
 */
public class LectureManagerImpl implements ILectureManager {
    private EntityManager entityManager;

    public LectureManagerImpl() {
        entityManager = EntityManagerUtil.getEntityManager();
    }

    @Override
    public void addLecture(Lecture lecture) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(lecture);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception ex) {
            }
            throw new RuntimeException("UNABLE TO ADD PERSON", e);
        }
    }

    @Override
    public List<Lecture> getLecturesByName(String name) {
        try {
            Query query = entityManager.createNamedQuery("selectByName");
            query.setParameter("name","%"+name+"%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("UNABLE TO GET LECTURES", e);
        }

    }

}
