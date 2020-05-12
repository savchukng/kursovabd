package dinoco.kursova.bd.service;

import dinoco.kursova.bd.exception.NotFoundException;
import dinoco.kursova.bd.model.SupplierProduct;
import dinoco.kursova.bd.model.SupplyOrder;
import dinoco.kursova.bd.model.SupplyOrderProduct;
import dinoco.kursova.bd.repository.SupplyOrderProductRepository;
import dinoco.kursova.bd.repository.SupplyOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final SupplyOrderProductRepository supplyOrderProductRepository;
    private final SupplierService supplierService;
    private final EmployeeService employeeService;

    public Integer save(SupplyOrder supplyOrder, Integer employeeId) {
        supplyOrder.setEmployee(employeeService.get(employeeId));
        supplyOrder.setCreationTime(LocalDateTime.now());
        return supplyOrderRepository.save(supplyOrder).getId();
    }

    public List<SupplyOrder> getAll() {
        return supplyOrderRepository.findAll();
    }

    public SupplyOrder get(Integer id) {
        return supplyOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Замовлення не знайдено"));
    }

    public void updateProduct(Integer id, Integer amount) {
        SupplyOrderProduct supplyOrderProduct = supplyOrderProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Продукт замовлення не знайдено"));
        supplyOrderProduct.setAmount(amount);
        supplyOrderProductRepository.save(supplyOrderProduct);
    }

    public void saveProduct(SupplyOrderProduct supplyOrderProduct, Integer supplierProductId) {
        SupplierProduct supplierProduct = supplierService.getProduct(supplierProductId);
        supplyOrderProduct.setPrice(supplierProduct.getPrice());
        supplyOrderProduct.setProduct(supplierProduct.getProduct());
        supplyOrderProductRepository.save(supplyOrderProduct);
    }

}
