package com.example.demo.models;

import javax.persistence.*;
import java.io.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startDate;
    private Date endDate;

    private String label;
    private String question;
    private String title;
    private String answer;

    @ElementCollection(targetClass = Type.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "type",joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Type> types;

    public Survey(String label, String question, String title, String answer, Set<Type> types) throws IOException {
        FileOutputStream fos = new FileOutputStream("question.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(answer);
        File resource = new File("./question.txt");
        String resFile = resource.toString();



        this.label = label;
        this.question = question;
        this.title = title;
        this.answer = resFile;
        this.types = types;
    }

    public Survey() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String lable) {
        this.label = lable;
    }
}
