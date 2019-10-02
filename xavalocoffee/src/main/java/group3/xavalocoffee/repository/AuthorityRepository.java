package group3.xavalocoffee.repository;

import group3.xavalocoffee.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findById(int id);
}
