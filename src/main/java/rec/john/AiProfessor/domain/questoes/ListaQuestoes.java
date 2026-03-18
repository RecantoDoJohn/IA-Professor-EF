package rec.john.AiProfessor.domain.questoes;

import java.util.List;

public record ListaQuestoes(
        List<QuestaoObjetiva> questoesObjetivas,
        List<QuestaoSubjetiva> questoesSubjetivas
) {
}
