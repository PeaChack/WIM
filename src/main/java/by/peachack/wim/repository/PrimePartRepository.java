package by.peachack.wim.repository;

import by.peachack.wim.model.inventory.PrimePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PrimePartRepository extends JpaRepository<PrimePart, UUID> {
}
