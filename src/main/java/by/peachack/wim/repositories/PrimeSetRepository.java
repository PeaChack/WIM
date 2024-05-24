package by.peachack.wim.repositories;

import by.peachack.wim.model.inventory.PrimeSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PrimeSetRepository extends JpaRepository<PrimeSet, UUID> {
}
