package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.AmountType;
import dinoco.kursova.bd.repository.AmountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmountTypeService {

    private final AmountTypeRepository amountTypeRepository;

    public List<AmountType> getAll() {
        return amountTypeRepository.findAll();
    }

}
