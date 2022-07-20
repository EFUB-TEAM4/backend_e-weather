package efub.team4.backend_eweather.domain.sky.service;

import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyAlreadyExistsException;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SkyService {
    private final SkyRepository skyRepository;

    @Transactional
    public Sky save(Sky sky) {
        skyRepository.findBySkyName(sky.getSkyName())
                .ifPresent((existedSky) -> {
                    throw new SkyAlreadyExistsException("Sky already exists with specified sky name");
                });
        return skyRepository.save(sky);
    }

    @Transactional(readOnly = true)
    public Sky findBySkyCode(Integer skyCode) {
        return skyRepository.findBySkyCode(skyCode)
                .orElseThrow(() -> new SkyNotFoundException("Sky not found with skyCode = " + skyCode));
    }
}
