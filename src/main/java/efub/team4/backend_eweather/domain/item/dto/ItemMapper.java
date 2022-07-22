package efub.team4.backend_eweather.domain.item.dto;

import efub.team4.backend_eweather.domain.item.entity.Item;
import efub.team4.backend_eweather.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    private final ItemRepository itemRepository;

    public Item createRequestDtoToEntity(ItemDto.ItemCreateDto reqeustDto) {
        return Item.builder()
                .item(reqeustDto.getItem())
                .build();
    }

    public ItemDto.ItemResponseDto fromEntity(Item entity) {
        return ItemDto.ItemResponseDto.builder()
                .id(entity.getId())
                .item(entity.getItem())
                .build();
    }

    public Item stringToEntity(String item) {
        Optional<Item> itemOptional = itemRepository.findByItem(item);

        if (itemOptional.isEmpty()) {
            return Item.builder()
                    .item(item)
                    .build();
        }

        return itemOptional.get();
    }
}
