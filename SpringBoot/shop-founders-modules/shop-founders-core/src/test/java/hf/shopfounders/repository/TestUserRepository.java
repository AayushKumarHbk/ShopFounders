package hf.shopfounders.repository;

import hf.shopfounders.dao.DaoUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class TestUserRepository implements UserRepository {
    @Override
    public DaoUser findDaoUserByUsername(String username) {
        return null;
    }

    @Override
    public <S extends DaoUser> List<S> save(Iterable<S> entites) {
        return null;
    }

    @Override
    public <S extends DaoUser> S save(S entity) {
        return null;
    }

    @Override
    public DaoUser findOne(String s) {
        return null;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public List<DaoUser> findAll() {
        return null;
    }

    @Override
    public Iterable<DaoUser> findAll(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(DaoUser entity) {

    }

    @Override
    public void delete(Iterable<? extends DaoUser> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DaoUser> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DaoUser> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DaoUser> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends DaoUser> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DaoUser> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends DaoUser> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends DaoUser> S findOne(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DaoUser> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DaoUser> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DaoUser> boolean exists(Example<S> example) {
        return false;
    }
}
