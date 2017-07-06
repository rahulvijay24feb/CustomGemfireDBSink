package hello.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.vo.PersonDB;
@Repository
public interface PersonDBRepository extends CrudRepository<PersonDB, String> {

    PersonDB findByName(String name);

    Iterable<PersonDB> findByAgeGreaterThan(int age);

    Iterable<PersonDB> findByAgeLessThan(int age);

    Iterable<PersonDB> findByAgeGreaterThanAndAgeLessThan(int age1, int age2);
}