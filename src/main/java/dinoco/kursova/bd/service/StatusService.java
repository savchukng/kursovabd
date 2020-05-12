package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.Status;
import dinoco.kursova.bd.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public Status get(Integer id) {
        return statusRepository.getOne(id);
    }

}
