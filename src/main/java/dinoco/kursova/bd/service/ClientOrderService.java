package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.ClientOrder;
import dinoco.kursova.bd.repository.ClientOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientOrderService {

    private final ClientOrderRepository clientOrderRepository;
    private final EmployeeService employeeService;

    public List<ClientOrder> getClientOrders(String paid) {
        return clientOrderRepository.findAllByPaymentStatus(Boolean.valueOf(paid));
    }

    public Integer add(ClientOrder clientOrder) {
        clientOrder.setSum(0);
        clientOrder.setWaiter(employeeService.get(1));
        clientOrder.setOrderDate(LocalDateTime.now());
        clientOrder.setPaymentStatus(false);
        return clientOrderRepository.save(clientOrder).getId();
    }

    public void save(ClientOrder clientOrder) {
        clientOrderRepository.save(clientOrder);
    }

    public ClientOrder get(Integer id) {
        return clientOrderRepository.getOne(id);
    }

    public void pay(Integer id) {
        ClientOrder clientOrder = clientOrderRepository.getOne(id);
        clientOrder.setPaymentStatus(true);
        clientOrderRepository.save(clientOrder);
    }

}
