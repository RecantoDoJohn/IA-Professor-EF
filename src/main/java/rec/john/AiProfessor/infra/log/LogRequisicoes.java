package rec.john.AiProfessor.infra.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

@ControllerAdvice
public class LogRequisicoes extends RequestBodyAdviceAdapter {

    private final Logger logger = LoggerFactory.getLogger(LogRequisicoes.class);
    private final AtomicInteger contadorRequisicoes = new AtomicInteger(0);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        int totalAtual = contadorRequisicoes.incrementAndGet();

        try {
            String jsonRecebido = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);


            logger.info("NOVA REQUISIÇÃO RECEBIDA - Total até agora: #{}", totalAtual);
            logger.info("Corpo (Payload): \n{}", jsonRecebido);

        } catch (JsonProcessingException e) {
            logger.error("Falha ao tentar registrar o log da requisição", e);
        }

        return body;

    }
}