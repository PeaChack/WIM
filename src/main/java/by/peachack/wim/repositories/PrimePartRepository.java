package by.peachack.wim.repositories;

import by.peachack.wim.model.items.PrimePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimePartRepository extends JpaRepository<PrimePart, String> {
}
