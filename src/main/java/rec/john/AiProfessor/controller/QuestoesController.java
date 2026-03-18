package rec.john.AiProfessor.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rec.john.AiProfessor.domain.ia.GeminiService;
import rec.john.AiProfessor.domain.questoes.DadosListaQuestoesPROMPT;
import rec.john.AiProfessor.domain.questoes.ListaQuestoes;

import javax.validation.Valid;

@RestController
@RequestMapping("/gerarQuestoes")
@RequiredArgsConstructor
public class QuestoesController  {

    private final GeminiService geminiService;



    @GetMapping
    public ResponseEntity<ListaQuestoes> gerarQuestoes(@RequestBody @Valid DadosListaQuestoesPROMPT prompt) {
        ListaQuestoes listaQuestoes = geminiService.gerarListaQuestoes(prompt);

        return ResponseEntity.ok(listaQuestoes);
    }
}
