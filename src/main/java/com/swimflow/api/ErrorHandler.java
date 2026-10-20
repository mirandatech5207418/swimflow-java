package com.swimflow.api;
import com.swimflow.service.BusinessException;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import org.springframework.web.bind.MethodArgumentNotValidException;import java.util.*;
@RestControllerAdvice public class ErrorHandler {
 @ExceptionHandler(BusinessException.class) public ResponseEntity<Map<String,String>> business(BusinessException e){return ResponseEntity.status(e.status).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,String>> invalid(MethodArgumentNotValidException e){return ResponseEntity.badRequest().body(Map.of("error","Dados inválidos: "+e.getBindingResult().getFieldErrors().stream().map(x->x.getField()).distinct().toList()));}
}
