package dinoco.kursova.bd.service;

import dinoco.kursova.bd.exception.NotFoundException;
import dinoco.kursova.bd.model.Supply;
import dinoco.kursova.bd.model.SupplyOrder;
import dinoco.kursova.bd.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SupplyService {

    private final SupplyRepository supplyRepository;
    private final SupplyOrderService supplyOrderService;
    private final EmployeeService employeeService;

    public Integer add(Integer supplyOrderId, Integer employeeId) {
        Supply supply = Supply.builder()
                .supplyOrder(supplyOrderService.get(supplyOrderId))
                .deliveryTime(LocalDateTime.now())
                .responsibleEmployee(employeeService.get(employeeId))
                .build();
        return supplyRepository.save(supply).getId();
    }

    public Supply get(Integer id) {
        return supplyRepository.findById(id).orElseThrow(() -> new NotFoundException("Поставка не знайдена"));
    }

    public Supply getBySupplyOrder(SupplyOrder supplyOrder) {
        return supplyRepository.findBySupplyOrder(supplyOrder).orElse(null);
    }

}
