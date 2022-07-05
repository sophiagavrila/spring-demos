package com.revature.quizzard.repositories;

import com.revature.quizzard.models.AppUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements CrudRepository<AppUser> {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory factory) {
        System.out.println("UserRepository#new invoked!");
        sessionFactory = factory;
    }

    public Optional<AppUser> findUserByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser au where au.username = :un and au.password = :pw", AppUser.class)
                .setParameter("un", username)
                .setParameter("pw", password)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Optional<AppUser> findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser au where au.username = :un", AppUser.class)
                .setParameter("un", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Optional<AppUser> findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser au where au.email = :email", AppUser.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<AppUser> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser", AppUser.class).getResultList();
    }

    @Override
    public Optional<AppUser> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(AppUser.class, id));
    }

    @Override
    public Optional<AppUser> save(AppUser newObj) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newObj);
        return Optional.of(newObj);
    }

    @Override
    public boolean update(AppUser updatedObj) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

}
