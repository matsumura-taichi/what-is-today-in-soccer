package jp.co.nichiwasystem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

  // find methods
  public List<Person> findByName(String name);
  public List<Person> findByTel(String tel);
  public List<Person> findByMail(String mail);
  public List<Person> findByDescription(String description);

}
