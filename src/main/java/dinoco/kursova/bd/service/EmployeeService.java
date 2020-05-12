package dinoco.kursova.bd.service;

import dinoco.kursova.bd.exception.NotFoundException;
import dinoco.kursova.bd.model.Employee;
import dinoco.kursova.bd.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee get(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Працівник не знайдений"));
    }

}
