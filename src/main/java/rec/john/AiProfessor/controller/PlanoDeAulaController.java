package rec.john.AiProfessor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rec.john.AiProfessor.domain.ia.GeminiService;
import rec.john.AiProfessor.domain.planosAula.DadosPlanoDeAulo;
import rec.john.AiProfessor.domain.planosAula.PlanoDeAula;

import javax.validation.Valid;

@RestController
@RequestMapping("/gerarPlanoAula")
@RequiredArgsConstructor
public class PlanoDeAulaController {
    private final GeminiService geminiService;

    @PostMapping
    public ResponseEntity<PlanoDeAula> gerarPlanodeAula(@RequestBody @Valid DadosPlanoDeAulo prompt) {
        PlanoDeAula planoDeAula = geminiService.gerarPlanoDeAula(prompt);

        return ResponseEntity.ok(planoDeAula);
    }

}
