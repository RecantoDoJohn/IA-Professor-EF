package rec.john.AiProfessor.domain.planosAula;

import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public record DadosPlanoDeAulo(
//        Obrigatorios
        @NotBlank(message = "Habilidade BNCCC e obrigatoria")
        String habilidadeBNCC,
        @NotBlank(message = "Ano e obrigatorio")
        String anoFundamental,
        @NotBlank(message = "Area do Conhecimento e obrigatoria")
        String areaConhecimento,

//        Opcionais
        String duracao,
        String recursosDidaticos,
        String metodologia,
        String avaliacao
) {
}
