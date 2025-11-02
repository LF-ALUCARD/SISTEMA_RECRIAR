package recriar.gestao.service.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import recriar.gestao.entities.enums.exceptions.CodicoInvalidoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CriacaoNegadaException.class)
	public ResponseEntity<Map<String,Object>> handlerCriacaoNegada(CriacaoNegadaException ex){
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("error", "CRIAÇÃO NEGADA");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}
	
	@ExceptionHandler(CredenciaisInvalidasException.class)
	public ResponseEntity<Map<String,Object>> handlerCredencialInvalida(CredenciaisInvalidasException ex){
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("error", "ACESSO NEGADO");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler(UsuarioInexistenteException.class)
	public ResponseEntity<Map<String,Object>> handlerCredencialInvalida(UsuarioInexistenteException ex){
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("error", "USUÁRIO NÃO ENCONTRADO");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler(BancoDeDadosExceptions.class)
	public ResponseEntity<Map<String,Object>> handlerBancoDeDados(BancoDeDadosExceptions ex){
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("error", "BANCO NÃO CONECTADO");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
	
	@ExceptionHandler(CodicoInvalidoException.class)
	public ResponseEntity<Map<String,Object>> handlerOpcaoSexo(CodicoInvalidoException ex){
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("error", "TIPO DE SEXO INVÁLIDO");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler(BloqueioDeleteException.class)
	public ResponseEntity<Map<String,Object>> handlerDeleteErrado(BloqueioDeleteException ex){
		Map<String, Object> body = new HashMap<>();
		body.put("message", ex.getMessage());
		body.put("error", "USÁRIO VINCULADO");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
	}
}
