package efub.team4.backend_eweather.domain.pty.dto;

import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PtyMapper {
    private final PtyRepository ptyRepository;

    public Pty createRequestDtoToEntity(PtyDto.PtyCreateDto requestDto){
        return Pty.builder()
                .ptyName(requestDto.getPtyName())
                .ptyCode(requestDto.getPtyCode())
                .ptyBackGroundFileUrl(requestDto.getPtyBackGroundFileUrl())
                .build();
    }

    public PtyDto.PtyResponseDto fromEntity(Pty entity){
        return PtyDto.PtyResponseDto.builder()
                .id(entity.getId())
                .ptyName(entity.getPtyName())
                .ptyCode(entity.getPtyCode())
                .build();
    }

    public PtyDto.PtyResponseDtoWithUrl getUrlFromEntity(Sky entity){
        return PtyDto.PtyResponseDtoWithUrl.builder()
                .id(entity.getId())
                .ptyName(entity.getSkyName())
                .ptyBackGroundFileUrl(entity.getSkyBackGroundFileUrl())
                .build();
    }

    public Pty codeToEntity(Integer ptyCode){
        Optional<Pty> ptyOptional = ptyRepository.findByPtyCode(ptyCode);
        return ptyOptional.get();
    }
}
