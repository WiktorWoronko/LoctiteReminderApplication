package pl.LoctiteRemainder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.LoctiteRemainder.model.Adhesive;

import java.util.List;

@Repository
public interface AdhesiveRepository extends JpaRepository<Adhesive,Integer> {
    @Query("select c from Adhesive c " +
            "where lower(c.productName) like lower(concat('%', :searchTerm, '%'))")
    Iterable<Adhesive> search(@Param("searchTerm")String filterText);

}
