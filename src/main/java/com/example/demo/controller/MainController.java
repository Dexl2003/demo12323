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


    //Репозитории для работы с БД
    @Autowired
    private SurveyRepository surveyRepository;

    //Домашняя страница
    @GetMapping("/")
    public String home(Model model) {
        //Ищем все записи в таблице с опросами и выводим их
        Iterable<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveyList", surveys);

        return "home";
    }

    //Страница авторизации
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    //Страница для добавления новых опросов
    @GetMapping("/survey")
    public String answer(Model model){
        return "survey";
    }


    //Обработчик добавления опросов
    @PostMapping("/survey")
    public String answer(Model model, @RequestParam String title, @RequestParam String label, @RequestParam String question, @RequestParam Set<Type> types , @RequestParam String answer , @RequestParam String startDate, @RequestParam String endDate) throws IOException, ParseException {
        //Присваиваем значения полям
        Survey survey = new Survey(title, label, question, answer, types,startDate,endDate);
        surveyRepository.save(survey);

        return "survey";
    }
    //Обработчик удаления записи по ее ID
    @PostMapping("/survey/{id}/delete")
    public String answerDelete(@PathVariable(value = "id") long id, Model model) throws ClassNotFoundException {
        //Ищем id записи
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException());
        //Удаляем запись в таблице
        surveyRepository.delete(survey);

        return "redirect:/";
    }

    //Страница с обновлением записи по ее ID
    @GetMapping("/survey/{id}/update")
    public String answerUp(@PathVariable(value = "id") long id, Model model) throws ClassNotFoundException {
        //Ищем запись по ее id и записываем ее
        Optional<Survey> survey = surveyRepository.findById(id);
        //Создаем массив из таблици
        ArrayList<Survey> result = new ArrayList<>();
        survey.ifPresent(result::add);
        //Передаем массив на страницу
        model.addAttribute("survey", result);
        return "survey-update";
    }
    //Обработчик обновления записи
    @PostMapping("/survey/{id}/update")
    public String answerUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String label, @RequestParam String question, @RequestParam Set<Type> types , @RequestParam String answer , @RequestParam String endDate) throws ClassNotFoundException {
        //Ищем id записи
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException());
        //Принимаем и записываем все поля в таблицу
        survey.setTitle(title);
        survey.setLabel(label);
        survey.setQuestion(question);
        survey.setEndDate(endDate);
        survey.setTypes(types);
        survey.setAnswer(answer);
        //Сохроняем изменения
        surveyRepository.save(survey);

        return "redirect:/";
    }



}
