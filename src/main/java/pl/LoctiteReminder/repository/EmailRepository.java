package pl.LoctiteReminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.LoctiteReminder.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email,Integer> {
}
