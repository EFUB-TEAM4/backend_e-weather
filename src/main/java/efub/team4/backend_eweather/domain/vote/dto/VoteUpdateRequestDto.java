package efub.team4.backend_eweather.domain.vote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoteUpdateRequestDto {
    String clothes;

    public VoteUpdateRequestDto(String clothes){
        this.clothes = clothes;
    }

}