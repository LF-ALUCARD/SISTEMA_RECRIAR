package recriar.gestao.repositories.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CriacaoNegadaException.class)
	public ResponseEntity<Map<String,Object>> handlerCriacaoNegada(CriacaoNegadaException ex){
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("error", "CRIAÇÃO NEGADA");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}
}
