package rec.john.AiProfessor.domain.questoes;

import javax.validation.constraints.NotNull;

public record DadosListaQuestoes(
        @NotNull
        String habilidadeBNCC,
        @NotNull
        String anoFundamental,
        @NotNull
        String areaConhecimento,

        // isso provavelmente vou tranformar em Enum
        String dificuldade,
        int quantQuestObj,
        int quantQuestSub
) {
}