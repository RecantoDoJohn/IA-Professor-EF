package rec.john.AiProfessor.domain.planosAula;

import javax.validation.constraints.NotNull;

public record DadosPlanoDeAulo(
//        Obrigatorios
        @NotNull
        String habilidadeBNCC,
        @NotNull
        String anoFundamental,
        @NotNull
        String areaConhecimento,

//        Opcionais
        String duracao,
        String recursosDidaticos,
        String metodologia,
        String avaliacao
) {
}
