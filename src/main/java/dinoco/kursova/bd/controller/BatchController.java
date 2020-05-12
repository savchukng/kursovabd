package dinoco.kursova.bd.controller;

import dinoco.kursova.bd.model.Batch;
import dinoco.kursova.bd.model.SupplyOrder;
import dinoco.kursova.bd.service.BatchService;
import dinoco.kursova.bd.service.SupplyOrderService;
import dinoco.kursova.bd.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/batches")
public class BatchController {

    private final BatchService batchService;

    @PostMapping("/add")
    public String add(Batch batch, @RequestParam("supplyId") Integer supplyId) {
        batchService.add(batch);
        return "redirect:/supplies/" + supplyId + "/batches";
    }

}
