package com.swimflow.api;
import com.swimflow.model.*;import com.swimflow.service.*;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import java.util.*;
@RestController @RequestMapping("/api/sessions") public class SessionController {
 private final SessionService service;public SessionController(SessionService service){this.service=service;}
 @GetMapping public List<SwimSession> list(){return service.list();}
 @GetMapping("/{id}") public SwimSession get(@PathVariable Long id){return service.get(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public SwimSession create(@Valid @RequestBody Requests.Session r){return service.create(r);}
 @PostMapping("/{id}/enrollments") @ResponseStatus(HttpStatus.CREATED) public Enrollment enroll(@PathVariable Long id,@Valid @RequestBody Requests.Enrollment r){return service.enroll(id,r.swimmerEmail());}
 @DeleteMapping("/{id}/enrollments/{enrollmentId}") @ResponseStatus(HttpStatus.NO_CONTENT) public void cancel(@PathVariable Long id,@PathVariable Long enrollmentId){service.cancel(id,enrollmentId);}
}
