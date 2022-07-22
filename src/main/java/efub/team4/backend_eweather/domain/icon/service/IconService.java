package efub.team4.backend_eweather.domain.icon.service;

import efub.team4.backend_eweather.domain.icon.entity.Icon;
import efub.team4.backend_eweather.domain.icon.exception.IconNotFoundException;
import efub.team4.backend_eweather.domain.icon.repository.IconRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IconService {
    private final IconRepository iconRepository;

    @Transactional
    public Icon save(Icon icon) {
        iconRepository.findById(icon.getId())
                .ifPresent((existedIcon) -> {
                    throw new IconNotFoundException("Icon already exists with specified id= " + icon.getId());
                });
        return iconRepository.save(icon);
    }
}
