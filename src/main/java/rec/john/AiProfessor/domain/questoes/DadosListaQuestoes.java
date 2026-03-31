package rec.john.AiProfessor.domain.questoes;

import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public record DadosListaQuestoes(
        @NotBlank(message = "Habilidade BNCCC e obrigatoria")
        String habilidadeBNCC,
        @NotBlank(message = "Ano e obrigatorio")
        String anoFundamental,
        @NotBlank(message = "Area do Conhecimento e obrigatoria")
        String areaConhecimento,

        String dificuldade,
        String tamanho,
        int quantQuestObj,
        int quantQuestSub
) {
}