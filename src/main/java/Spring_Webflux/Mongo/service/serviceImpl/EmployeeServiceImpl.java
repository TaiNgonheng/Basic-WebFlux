package Spring_Webflux.Mongo.service.serviceImpl;

import Spring_Webflux.Mongo.dto.EmployeeDto;
import Spring_Webflux.Mongo.entity.Employee;
import Spring_Webflux.Mongo.mapper.EmployeeMapper;
import Spring_Webflux.Mongo.repository.EmployeeRepository;
import Spring_Webflux.Mongo.service.EmployeeService;
import lombok.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Mono<Employee> employeeMono= employeeRepository.save(employee);
        return employeeMono.map((employeeEntity)-> EmployeeMapper.mapToEmployeeDto(employeeEntity));
    }

    @Override
    public Mono<EmployeeDto> getEmployee(String employeeId) {

        Mono<Employee> savedEmployee = employeeRepository.findById(employeeId);
        return savedEmployee.map((employee)-> EmployeeMapper.mapToEmployeeDto(employee));
    }

    @Override
    public Flux<EmployeeDto> getAllEmployee() {
        Flux<Employee> savedAllEmployee = employeeRepository.findAll();
        return savedAllEmployee.map((employee)->EmployeeMapper.mapToEmployeeDto(employee));
    }

    @Override
    public Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId) {
       Mono<Employee> employeeMono = employeeRepository.findById(employeeId);
       Mono<Employee> employeeMono1 =employeeMono.flatMap((existingEmployee)->{
          existingEmployee.setFirstname(employeeDto.getFirstname());
          existingEmployee.setLastname(employeeDto.getLastname());
          existingEmployee.setEmail(employeeDto.getEmail());

          return employeeRepository.save(existingEmployee);
       });
        return employeeMono1.map((employee)-> EmployeeMapper.mapToEmployeeDto(employee));
    }

    @Override
    public Mono<Void> deleteEmployee(String employeeId) {
        return employeeRepository.deleteById(employeeId);
    }
}
