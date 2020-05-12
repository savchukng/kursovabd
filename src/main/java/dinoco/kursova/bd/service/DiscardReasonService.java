package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.DiscardReason;
import dinoco.kursova.bd.repository.DiscardReasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscardReasonService {

    private final DiscardReasonRepository discardReasonRepository;

    public List<DiscardReason> getAll() {
        return discardReasonRepository.findAll();
    }

}
