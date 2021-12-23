package com.example.demo.controller;

import com.example.demo.models.Survey;
import com.example.demo.models.Type;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {

        Iterable<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveyList", surveys);

        return "home";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


//Add answer
    @GetMapping("/answer")
    public String answer(Model model){
        return "answer";
    }



//Add answer
    @PostMapping("/answer")
    public String answer(Model model, @RequestParam String title, @RequestParam String label, @RequestParam String question, @RequestParam Set<Type> types , @RequestParam String answer , @RequestParam String startDate, @RequestParam String endDate) throws IOException, ParseException {

        Survey survey = new Survey(title, label, question, answer, types,startDate,endDate);
        surveyRepository.save(survey);

        return "answer";
    }

    @PostMapping("/answer/{id}/delete")
    public String answerDelete(@PathVariable(value = "id") long id, Model model) throws ClassNotFoundException {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException());

        surveyRepository.delete(survey);

        return "redirect:/";
    }

    @GetMapping("/answer/{id}/update")
    public String answerUp(@PathVariable(value = "id") long id, Model model) throws ClassNotFoundException {
        Optional<Survey> survey = surveyRepository.findById(id);

        ArrayList<Survey> result = new ArrayList<>();
        survey.ifPresent(result::add);

        model.addAttribute("survey", result);
        return "answer-update";
    }

    @PostMapping("/answer/{id}/update")
    public String answerUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String label, @RequestParam String question, @RequestParam Set<Type> types , @RequestParam String answer , @RequestParam String endDate) throws ClassNotFoundException {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException());

        survey.setTitle(title);
        survey.setLabel(label);
        survey.setQuestion(question);
        survey.setEndDate(endDate);
        survey.setTypes(types);
        survey.setAnswer(answer);

        surveyRepository.save(survey);

        return "redirect:/";
    }



}
