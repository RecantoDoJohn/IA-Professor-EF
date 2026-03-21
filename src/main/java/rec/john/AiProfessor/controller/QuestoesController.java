package rec.john.AiProfessor.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rec.john.AiProfessor.domain.ia.GeminiService;
import rec.john.AiProfessor.domain.questoes.DadosListaQuestoes;
import rec.john.AiProfessor.domain.questoes.ListaQuestoes;

import javax.validation.Valid;

@RestController
@RequestMapping("/gerarQuestoes")
@RequiredArgsConstructor
public class QuestoesController  {

    private final GeminiService geminiService;



    @PostMapping
    public ResponseEntity<ListaQuestoes> gerarQuestoes(@RequestBody @Valid DadosListaQuestoes prompt) {
        ListaQuestoes listaQuestoes = geminiService.gerarListaQuestoes(prompt);

        return ResponseEntity.ok(listaQuestoes);
    }
}
