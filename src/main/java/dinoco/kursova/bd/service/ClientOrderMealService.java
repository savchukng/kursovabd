package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.ClientOrder;
import dinoco.kursova.bd.model.ClientOrderMeal;
import dinoco.kursova.bd.repository.ClientOrderMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientOrderMealService {

    private final ClientOrderMealRepository clientOrderMealRepository;
    private final ClientOrderService clientOrderService;
    private final StatusService statusService;
    private final MealCookingService mealCookingService;

    public List<ClientOrderMeal> getByClientOrderId(Integer id) {
        return clientOrderMealRepository.findAllByClientOrder_Id(id);
    }

    public void add(ClientOrderMeal clientOrderMeal) {
        clientOrderMeal.setStatus(statusService.get(1));
        ClientOrder clientOrder = clientOrderService.get(clientOrderMeal.getClientOrder().getId());
        clientOrder.setSum(clientOrder.getSum() + clientOrderMeal.getMeal().getPrice() * clientOrderMeal.getQuantity());
        clientOrderMealRepository.save(clientOrderMeal);
        clientOrderService.save(clientOrder);
    }

    public void delete(Integer id) {
        ClientOrderMeal clientOrderMeal = clientOrderMealRepository.getOne(id);
        ClientOrder clientOrder = clientOrderMeal.getClientOrder();
        clientOrder.setSum(clientOrder.getSum() - clientOrderMeal.getMeal().getPrice() * clientOrderMeal.getQuantity());
        clientOrderService.save(clientOrder);
        clientOrderMealRepository.deleteById(id);
    }

    public Integer startCooking(Integer id, Integer employeeId) {
        ClientOrderMeal clientOrderMeal = clientOrderMealRepository.getOne(id);
        clientOrderMeal.setStatus(statusService.get(2));
        clientOrderMealRepository.save(clientOrderMeal);
        mealCookingService.add(clientOrderMeal, employeeId);
        return clientOrderMeal.getClientOrder().getId();
    }

    public Integer ready(Integer id) {
        ClientOrderMeal clientOrderMeal = clientOrderMealRepository.getOne(id);
        clientOrderMeal.setStatus(statusService.get(3));
        clientOrderMealRepository.save(clientOrderMeal);
        return clientOrderMeal.getClientOrder().getId();
    }

}
