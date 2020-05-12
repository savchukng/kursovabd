package dinoco.kursova.bd.service;

import dinoco.kursova.bd.exception.NotFoundException;
import dinoco.kursova.bd.model.Supplier;
import dinoco.kursova.bd.model.SupplierProduct;
import dinoco.kursova.bd.repository.SupplierProductRepository;
import dinoco.kursova.bd.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierProductRepository supplierProductRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Supplier get(Integer id) {
        return supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Постачальника не знайдено"));
    }

    public SupplierProduct getProduct(Integer id) {
        return supplierProductRepository.findById(id).orElseThrow(() -> new NotFoundException("Продукт не знайдено"));
    }

    public List<SupplierProduct> getProducts(Supplier supplier) {
        return supplierProductRepository.findBySupplier(supplier);
    }

}
