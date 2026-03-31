package rec.john.AiProfessor.domain.questoes;

import java.util.List;

public record QuestaoObjetiva(
        String pergunta,
        List<String> alternativas,
        String letraCorreta,
        String explicacao
) {
}
