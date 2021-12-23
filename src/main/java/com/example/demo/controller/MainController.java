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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {

        //TODO Десириализация GSON


        Iterable<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveyList", surveys);

        return "home";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

//    @PostMapping("/login")
//    public String addUser(User user) {
//        user.setEnabled(true);
//        user.setRoles(Collections.singleton(Roles.ADMIN));
//        userRepository.save(user);
//        return "home";
//    }

//Add answer
    @GetMapping("/answer")
    public String answer(Model model){
        return "answer";
    }


//Add answer
    @PostMapping("/answer")
    public String answer(Model model, @RequestParam String title, @RequestParam String label, @RequestParam String question, @RequestParam Set<Type> types /*, @RequestParam String answer */, @RequestParam String startDate, @RequestParam String endDate) throws IOException, ParseException {

        //TODO Сириализация GSON

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MMM.dd",Locale.getDefault());
//        Date stdate = formatter.parse(startDate);
//        SimpleDateFormat


        Survey survey = new Survey(title, label, question,/* answer,*/ types,startDate,endDate);
        surveyRepository.save(survey);


        return "answer";
    }






}
