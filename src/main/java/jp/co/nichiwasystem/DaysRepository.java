package jp.co.nichiwasystem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysRepository extends JpaRepository<Days, Integer> {
	public List<Person> findByMonth(Integer month);
}
