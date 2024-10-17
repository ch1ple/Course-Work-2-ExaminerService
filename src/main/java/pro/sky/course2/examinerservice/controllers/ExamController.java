package pro.sky.course2.examinerservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.services.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerService service;

    @Autowired
    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping(path = "/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable(name = "amount") int amount) {
        return service.getQuestions(amount);
    }

}
