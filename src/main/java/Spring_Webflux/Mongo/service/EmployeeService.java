package Spring_Webflux.Mongo.service;

import Spring_Webflux.Mongo.dto.EmployeeDto;
import Spring_Webflux.Mongo.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);
    Mono<EmployeeDto> getEmployee(String employeeId);
    Flux<EmployeeDto> getAllEmployee();
    Mono<EmployeeDto> updateEmployee(EmployeeDto employeeDto, String employeeId);

    Mono<Void> deleteEmployee(String employeeId);
}
