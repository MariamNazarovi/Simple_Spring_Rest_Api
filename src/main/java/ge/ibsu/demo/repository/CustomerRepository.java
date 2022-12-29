package ge.ibsu.demo.repository;

import ge.ibsu.demo.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("From Customer where active = :active and " +
           "(:searchText is null or concat(firstName, concat(' ', lastName)) like :searchText)")
    Slice<Customer> search(@Param("active") Integer active, @Param("searchText") String searchText, Pageable pageable);

    @Query(value = "select * from customer where active = :active and" +
          "(:searchText is null or concat(first_name, concat('', last_name)) like :searchText)",
            countQuery = "select count(*) from customer where active = :active and" +
                    "(:searchText is null or concat(first_name,concat(' ', last_name)) like :searchText)",
            nativeQuery = true)
    List<Customer> searchWithNative(@Param("active") Integer active, @Param("searchText") String searchText);
}


