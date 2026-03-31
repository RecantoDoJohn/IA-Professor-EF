package rec.john.AiProfessor.domain.ia;

import jakarta.validation.constraints.Null;
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
        String prompt = String.format("Como um professor especialista do ensino fundamental, gere %d questões de múltipla escolha com as letras e %d questoes subjetivas com espacos de quebra de linha para a resposta ambos sobre a habilidade BNCC \"%s\" para alunos do %s do ensino fundamental. pode usar quebras de linha e pode fazer questoes com subitens e com o enunciado",
                promptRaw.quantQuestObj(), promptRaw.quantQuestSub(), promptRaw.habilidadeBNCC(), promptRaw.anoFundamental());

        if (promptRaw.dificuldade() != null) {
            prompt += String.format("as questoes devem ser de dificultade %s", promptRaw.dificuldade());
        }

        if (promptRaw.tamanho() != null) {
            prompt += String.format("as questoes devem ser de tamanho %s", promptRaw.tamanho());
        }

        return this.chatClient.prompt().user(prompt)
                .options(
                        GoogleGenAiChatOptions.builder()
                                .responseMimeType("application/json").build())
                .call()
                .entity(new ParameterizedTypeReference<ListaQuestoes>() {});
    }

    public PlanoDeAula gerarPlanoDeAula(DadosPlanoDeAulo promptRaw) {
        String prompt = String.format("Como um professor do ensino fundamental, gere um plano de aula com a habilidade %s seguindo a BNCC, para o %s ano do ensino fundamental da area de %s, pode usar quebras de linha",
                 promptRaw.habilidadeBNCC(), promptRaw.anoFundamental(), promptRaw.areaConhecimento());

        if (promptRaw.duracao() != null) {
            prompt += String.format("devem ser de duracao %s", promptRaw.duracao());
        }

        if (promptRaw.recursosDidaticos() != null) {
            prompt += String.format("devem usar %s como recursos didaticos", promptRaw.recursosDidaticos());
        }

        if (promptRaw.duracao() != null) {
            prompt += String.format("devem ser de duracao %s", promptRaw.duracao());
        }

        if (promptRaw.avaliacao() != null) {
            prompt += String.format("devem ter como avaliacao %s", promptRaw.avaliacao());
        }

        return this.chatClient.prompt().user(prompt)
                .options(
                        GoogleGenAiChatOptions.builder()
                                .responseMimeType("application/json").build())
                .call()
                .entity(new ParameterizedTypeReference<PlanoDeAula>() {});
    }

}
