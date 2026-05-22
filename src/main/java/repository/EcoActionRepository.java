package repository;

import model.EcoAction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EcoActionRepository extends JpaRepository<EcoAction, Long> {
    List<EcoAction> findByUser_Id(Long userId);
    long countByUser_Id(Long userId);
    List<EcoAction> findTop10ByUser_IdOrderByTimestampDesc(Long userId);
}