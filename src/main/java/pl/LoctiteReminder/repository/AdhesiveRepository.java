package pl.LoctiteReminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.LoctiteReminder.model.Adhesive;

@Repository
public interface AdhesiveRepository extends JpaRepository<Adhesive,Integer> {
    @Query("select c from Adhesive c " +
            "where lower(c.productName) like lower(concat('%', :searchTerm, '%'))")
    Iterable<Adhesive> search(@Param("searchTerm")String filterText);

}
