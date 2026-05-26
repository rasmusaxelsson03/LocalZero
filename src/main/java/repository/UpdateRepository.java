package repository;

import model.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UpdateRepository extends JpaRepository<Update, Long> {
    List<Update> findByInitiativeId(Long initiativeId);
}