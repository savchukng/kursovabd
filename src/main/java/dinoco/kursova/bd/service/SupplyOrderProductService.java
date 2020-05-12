package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.SupplyOrder;
import dinoco.kursova.bd.model.SupplyOrderProduct;
import dinoco.kursova.bd.repository.SupplyOrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyOrderProductService {

    private final SupplyOrderProductRepository supplyOrderProductRepository;

    public void save(SupplyOrderProduct supplyOrderProduct) {
        supplyOrderProductRepository.save(supplyOrderProduct);
    }

    public List<SupplyOrderProduct> getBySupplyOrder(SupplyOrder supplyOrder) {
        return supplyOrderProductRepository.findBySupplyOrder(supplyOrder);
    }

    public SupplyOrderProduct get(Integer id) {
        return supplyOrderProductRepository.getOne(id);
    }

    public Integer delete(Integer id) {
        SupplyOrderProduct supplyOrderProduct = supplyOrderProductRepository.getOne(id);
        Integer supplyOrderId = supplyOrderProduct.getSupplyOrder().getId();
        supplyOrderProductRepository.delete(supplyOrderProduct);
        return supplyOrderId;
    }

}
