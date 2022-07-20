package efub.team4.backend_eweather.domain.pty.service;

import efub.team4.backend_eweather.domain.pty.entity.Pty;
import efub.team4.backend_eweather.domain.pty.exception.PtyAlreadyExistsException;
import efub.team4.backend_eweather.domain.pty.exception.PtyNotFoundException;
import efub.team4.backend_eweather.domain.pty.repository.PtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PtyService {
    private final PtyRepository ptyRepository;

    @Transactional
    public Pty save(Pty pty) {
        ptyRepository.findById(pty.getId()).ifPresent((existedPty) -> {
            throw new PtyAlreadyExistsException("Pty already exists with specified pty id");
        });

        return ptyRepository.save(pty);
    }

    @Transactional(readOnly = true)
    public Pty findByPtyCode(Integer ptyCode) {
        return ptyRepository.findByPtyCode(ptyCode)
                .orElseThrow(() -> new PtyNotFoundException("Pty not found with ptyCode = " + ptyCode));
    }
}
