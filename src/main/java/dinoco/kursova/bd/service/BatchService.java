package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.Batch;
import dinoco.kursova.bd.model.RestaurantProduct;
import dinoco.kursova.bd.repository.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final SupplyOrderProductService supplyOrderProductService;
    private final RestaurantProductService restaurantProductService;

    public List<Batch> findBySupplyOrderProductId(Integer supplyOrderProductId) {
        return batchRepository.findAllBySupplyOrderProduct_Id(supplyOrderProductId);
    }

    public void add(Batch batch) {
        Integer wholeAmount = supplyOrderProductService.get(batch.getSupplyOrderProduct().getId()).getAmount();
        Integer maxAmount = wholeAmount - findBySupplyOrderProductId(batch.getSupplyOrderProduct().getId()).stream()
                .mapToInt(Batch::getAmount)
                .sum();
        if (batch.getAmount() > maxAmount) {
            batch.setAmount(maxAmount);
        }
        Batch savedBatch = batchRepository.save(batch);
        restaurantProductService.save(RestaurantProduct.builder().batch(savedBatch).amount(batch.getAmount()).build());
    }

}
