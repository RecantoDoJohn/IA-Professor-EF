package rec.john.AiProfessor.infra.exeption;

import com.google.genai.errors.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rec.john.AiProfessor.domain.ValidacaoExeption;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ValidacaoExeption.class)
    public ResponseEntity tratarRegraNegocio(ValidacaoExeption ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    public ResponseEntity tratarGemini(ClientException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("429")) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(new DadosErroGeral("Limite de requisições atingido. A IA está gerando muitas aulas no momento. Por favor, aguarde cerca de 30 segundos e tente novamente."));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DadosErroGeral("Erro de comunicação com a IA: " + ex.getMessage()));
    }


    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    private record DadosErroGeral(String erro) {}
}
