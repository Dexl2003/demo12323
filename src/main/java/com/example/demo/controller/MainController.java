package com.example.demo.controller;

import com.example.demo.models.Roles;
import com.example.demo.models.Survey;
import com.example.demo.models.Type;
import com.example.demo.models.User;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String addUser(User user) {
        user.setEnabled(true);
        user.setRoles(Collections.singleton(Roles.USER));
        userRepository.save(user);
        return "home";
    }

    @GetMapping("/answer")
    public String answer(Model model){
        return "answer";
    }



    @PostMapping("/answer")
    public String answer(@RequestParam String title, @RequestParam String question, @RequestParam String label, @RequestParam Set<Type> types, @RequestParam String answer) throws IOException {

        Survey survey = new Survey(title, label, question, answer, types);
        surveyRepository.save(survey);

        return "answer";
    }






}
