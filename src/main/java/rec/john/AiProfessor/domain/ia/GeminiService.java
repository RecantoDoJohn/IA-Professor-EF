package rec.john.AiProfessor.domain.ia;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rec.john.AiProfessor.domain.planosAula.DadosPlanoDeAulo;
import rec.john.AiProfessor.domain.planosAula.PlanoDeAula;
import rec.john.AiProfessor.domain.questoes.DadosListaQuestoes;
import rec.john.AiProfessor.domain.questoes.ListaQuestoes;

@Service
public class GeminiService {
    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ListaQuestoes gerarListaQuestoes(DadosListaQuestoes promptRaw) {
        String prompt = String.format("Como um professor do ensino fundamental, gere %d questões de múltipla escolha e %d questoes objetivas sobre a habilidade BNCC \"%s\" para alunos do %s do ensino fundamental.",
                promptRaw.quantQuestObj(), promptRaw.quantQuestSub(), promptRaw.habilidadeBNCC(), promptRaw.anoFundamental());

        return this.chatClient.prompt().user(prompt)
                .options(
                        GoogleGenAiChatOptions.builder()
                                .responseMimeType("application/json").build())
                .call()
                .entity(new ParameterizedTypeReference<ListaQuestoes>() {});
    }

    public PlanoDeAula gerarPlanoDeAula(DadosPlanoDeAulo promptRaw) {
        String prompt = String.format("Como um professor do ensino fundamental, gere um plano de aula com a(s) habilidade(s) %s seguindo a BNCC, para o %s ano do ensino fundamental da area de %s",
                 promptRaw.habilidadeBNCC(), promptRaw.anoFundamental(), promptRaw.areaConhecimento());

        return this.chatClient.prompt().user(prompt)
                .options(
                        GoogleGenAiChatOptions.builder()
                                .responseMimeType("application/json").build())
                .call()
                .entity(new ParameterizedTypeReference<PlanoDeAula>() {});
    }

}
