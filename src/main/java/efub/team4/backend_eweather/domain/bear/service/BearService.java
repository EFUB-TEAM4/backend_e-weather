package efub.team4.backend_eweather.domain.bear.service;

import efub.team4.backend_eweather.domain.bear.entity.Bear;
import efub.team4.backend_eweather.domain.bear.exception.BearNotFoundException;
import efub.team4.backend_eweather.domain.bear.repository.BearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BearService {
    private final BearRepository bearRepository;


    @Transactional
    public Bear save(Bear bear){
        bearRepository.findById(bear.getId())
                .ifPresent((existedBear) -> {
                    throw new BearNotFoundException("Bear already exists with specified id = " + bear.getId());
                });
        return bearRepository.save(bear);
    }

}
