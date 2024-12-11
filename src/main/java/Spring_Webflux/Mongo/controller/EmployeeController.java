package Spring_Webflux.Mongo.controller;

import Spring_Webflux.Mongo.dto.EmployeeDto;
import Spring_Webflux.Mongo.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private  EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Mono<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.saveEmployee(employeeDto);
    }

    @GetMapping("{id}/get")
    public Mono<EmployeeDto> getEmployeeByid(@PathVariable("id") String employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/All")
    public Flux<EmployeeDto> getAll(){
        return employeeService.getAllEmployee();
    }

    @PutMapping("{id}/update")
    public Mono<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable("id") String employeeId){
        return employeeService.updateEmployee(employeeDto,employeeId);
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String employeeId){
        System.out.println("This employee with this Id was delete SuccessFully@@@@##");
      return employeeService.deleteEmployee(employeeId);
    }
}
