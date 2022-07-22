package efub.team4.backend_eweather.domain.item.service;

import efub.team4.backend_eweather.domain.item.entity.Item;
import efub.team4.backend_eweather.domain.item.exception.ItemAlreadyExistsException;
import efub.team4.backend_eweather.domain.item.exception.ItemNotFoundException;
import efub.team4.backend_eweather.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item save(Item item) {
        itemRepository.findByItem(item.getItem())
                .ifPresent((existedItem) -> {
                    throw new ItemAlreadyExistsException("Item already exists with specified item name");
                });
        return itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Item findById(UUID id) {
        return (Item) itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id = " + id));
    }

    @Transactional(readOnly = true)
    public Item findByItem(String item) {
        return itemRepository.findByItem(item)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with item =  " + item));
    }

    @Transactional(readOnly = true)
    public List<Item> findAll(Pageable pageable) {
        return itemRepository.findAll();
    }

}
