package efub.team4.backend_eweather.domain.icon.service;

import efub.team4.backend_eweather.domain.dayNight.entity.DayNight;
import efub.team4.backend_eweather.domain.dayNight.exception.DayNightNotFoundException;
import efub.team4.backend_eweather.domain.dayNight.repository.DayNightRepository;
import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.exception.IconAlreadyExistsException;
import efub.team4.backend_eweather.domain.icon.exception.IconNotFoundException;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import efub.team4.backend_eweather.domain.sky.entity.Sky;
import efub.team4.backend_eweather.domain.sky.exception.SkyNotFoundException;
import efub.team4.backend_eweather.domain.sky.repository.SkyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IconService {
    private final IconRepository iconRepository;
    private final SkyRepository skyRepository;
    private final PtyRepository ptyRepository;
    private final DayNightRepository dayNightRepository;

    @Transactional
    public Icon save(Icon icon) {
        iconRepository.findIconByIconName(icon.getIconName())
                .ifPresent((existedIcon) -> {
                    throw new IconAlreadyExistsException("Icon already exists with specified id= " + icon.getId());
                });
        return iconRepository.save(icon);
    }

    @Transactional(readOnly = true)
    public Icon findById(UUID id) {
        return iconRepository.findById(id)
                .orElseThrow(() -> new IconNotFoundException("Icon not found with id"));
    }

    @Transactional(readOnly = true)
    public Icon findBySkyCodeAndPtyCode(Integer skyCode, Integer ptyCode, String forecastTime) {
        DayNight dayNight = dayNightRepository.findDayNightWithQueryByTime(forecastTime)
                .orElseThrow(() -> new DayNightNotFoundException("DayNight not found with time"));
        Sky sky = skyRepository.findSkyBySkyCodeAndDayNight_Id(skyCode, dayNight.getId())
                .orElseThrow(() -> new SkyNotFoundException("Sky not found with skyCode and dayNightId"));
        Pty pty = ptyRepository.findByPtyCode(ptyCode)
                .orElseThrow(() -> new PtyNotFoundException("Pty not found with ptyCode"));
        return iconRepository.findBySkyAndPty(sky, pty)
                .orElseThrow(() -> new IconNotFoundException("Icon not found with sky and pty"));
    }

    @Transactional(readOnly = true)
    public List<Icon> findAll() {
        return iconRepository.findAll();
    }


}
