package dinoco.kursova.bd.service;

import dinoco.kursova.bd.model.User;
import dinoco.kursova.bd.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return new User(employeeRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new UsernameNotFoundException(id)));
    }
}
