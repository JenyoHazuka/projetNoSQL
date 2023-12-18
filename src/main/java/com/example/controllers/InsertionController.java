package com.example.controllers;
import com.example.services.InsertionArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/inserer")
public class InsertionController {

    @Autowired
    private InsertionArticle insertionArticle;

    @GetMapping("/insertPage")
    public String afficherPageInsertion() {
        return "insertPage";
    }

    @PostMapping("/inserer")
    public String insererDonnee(@RequestParam String Namebar, @RequestParam int PriceUniquebar) {
        insertionArticle.insererArticle(Namebar, PriceUniquebar);
        return "redirect:/insertPage.html";
    }
}
