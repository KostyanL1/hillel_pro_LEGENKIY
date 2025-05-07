package org.lesson30.app;

import org.hibernate.Session;

import java.util.List;

public class StudentService implements GenericDao<Student, Long>{

    private final Session session;

    public StudentService(Session session) {
        this.session = session;
    }

    @Override
    public void save(Student entity) {
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
    }

    @Override
    public Student findById(Long aLong) {

        return session.createQuery("from Student where id=?", Student.class).setParameter(1, aLong).uniqueResult();
    }

    @Override
    public Student findByEmail(String email) {
        return session.createQuery("from Student where email=?", Student.class)
                .setParameter(1, email)
                .uniqueResult();
    }

    @Override
    public List<Student> findAll() {
        return session.createQuery("from Student", Student.class).list();
    }

    @Override
    public Student update(Student entity) {
        session.beginTransaction();
        Student update = session.merge(entity);
        session.getTransaction().commit();
        return update;
    }

    @Override
    public boolean deleteById(Long aLong) {
        session.beginTransaction();
        int result = session.createQuery("delete Student where id=?")
                .setParameter(1, aLong).executeUpdate();
        session.getTransaction().commit();
        return result > 0;
    }
}
