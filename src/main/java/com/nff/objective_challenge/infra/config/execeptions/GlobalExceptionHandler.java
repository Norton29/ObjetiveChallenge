package com.nff.objective_challenge.infra.config.execeptions;

import java.util.HashMap;
import java.util.Map;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(PSQLException.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("Erro ao cadastrar conta", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(GenericException.class)
  public ResponseEntity<Map<String, String>> handleGenericException(GenericException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BalanceException.class)
  public ResponseEntity<Map<String, String>> handleBalanceException(BalanceException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

}
