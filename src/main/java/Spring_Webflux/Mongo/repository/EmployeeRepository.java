package Spring_Webflux.Mongo.repository;

import Spring_Webflux.Mongo.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee,String> {
}
