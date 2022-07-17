package efub.team4.backend_eweather.domain.item.repository;

import efub.team4.backend_eweather.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findByItem(String item);
}
