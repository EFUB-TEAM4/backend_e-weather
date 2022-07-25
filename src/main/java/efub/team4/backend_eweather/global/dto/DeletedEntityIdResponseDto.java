package efub.team4.backend_eweather.global.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletedEntityIdResponseDto {
    @NotBlank
    private UUID id;
}

