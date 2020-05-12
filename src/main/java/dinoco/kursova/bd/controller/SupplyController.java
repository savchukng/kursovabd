package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.Batch;
import dinoco.kursova.bd.model.Supply;
import dinoco.kursova.bd.model.SupplyOrder;
import dinoco.kursova.bd.model.SupplyOrderProduct;
import dinoco.kursova.bd.service.BatchService;
import dinoco.kursova.bd.service.SupplyOrderProductService;
import dinoco.kursova.bd.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/supplies")
public class SupplyController {

    private final SupplyService supplyService;
    private final SupplyOrderProductService supplyOrderProductService;
    private final BatchService batchService;

    @GetMapping("/{id}/batches")
    public String add(Model model, @PathVariable Integer id) {
        Supply supply = supplyService.get(id);
        List<SupplyOrderProduct> supplyOrderProducts = supplyOrderProductService.getBySupplyOrder(supply.getSupplyOrder());
        List<Batch> existingBatches = new ArrayList<>();
        List<SupplyOrderProduct> fullyBatchedProducts = new ArrayList<>();
        supplyOrderProducts.forEach(supplyOrderProduct -> {
            List<Batch> batches = batchService.findBySupplyOrderProductId(supplyOrderProduct.getId());
            existingBatches.addAll(batches);
            Integer alreadyBatched = batches.stream()
                    .mapToInt(Batch::getAmount)
                    .sum();
            if (supplyOrderProduct.getAmount() <= alreadyBatched) {
                fullyBatchedProducts.add(supplyOrderProduct);
            } else {
                supplyOrderProduct.setAmount(supplyOrderProduct.getAmount() - alreadyBatched);
            }
        });
        supplyOrderProducts.removeAll(fullyBatchedProducts);
        Batch newBatch = supplyOrderProducts.isEmpty() ? null : new Batch();
        model.addAttribute("supplyOrderProducts", supplyOrderProducts);
        model.addAttribute("batches", existingBatches);
        model.addAttribute("newBatch", newBatch);
        model.addAttribute("supplyId", id);
        return "batches";
    }

}
