package rec.john.AiProfessor.domain.questoes;

import java.util.List;

public record QuestaoObjetiva(
        String enunciado,
        String pergunta,
        List<Alternativa> alternativas,
        String explicacao
) {
}
