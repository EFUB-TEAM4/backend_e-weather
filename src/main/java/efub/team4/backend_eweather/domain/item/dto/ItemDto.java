package efub.team4.backend_eweather.domain.item.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

public class ItemDto {
    @Getter
    @Setter
    public static class ItemCreateDto{
        @Size(max = 50)
        @NotEmpty
        private String item;
    }

    @Getter
    @Setter
    public static class ItemIdCreateDto{
        private UUID id;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemResponseDto{
        private UUID id;
        private String item;
    }
}
