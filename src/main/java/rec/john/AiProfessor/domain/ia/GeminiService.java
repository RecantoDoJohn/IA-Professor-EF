package rec.john.AiProfessor.domain.ia;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rec.john.AiProfessor.domain.questoes.DadosListaQuestoesPROMPT;
import rec.john.AiProfessor.domain.questoes.ListaQuestoes;

import java.util.List;

@Service
public class GeminiService {
    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ListaQuestoes gerarListaQuestoes(DadosListaQuestoesPROMPT promptRaw) {
        String prompt = String.format("Gere %d questões de múltipla escolha e %d questoes objetivas sobre a habilidade BNCC \"%s\" para alunos do %s do ensino fundamental.",
                promptRaw.quantQuestObj(), promptRaw.quantQuestSub(), promptRaw.habilidadeBNCC(), promptRaw.anoFundamental());

        return this.chatClient.prompt().user(prompt)
                .options(
                        GoogleGenAiChatOptions.builder()
                                .responseMimeType("application/json").build())
                .call()
                .entity(new ParameterizedTypeReference<ListaQuestoes>() {});
    }

}
