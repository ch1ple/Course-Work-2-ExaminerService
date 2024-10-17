package pro.sky.course2.examinerservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.services.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/math")
public class MathQuestionController {

    private final QuestionService service;

    @Autowired
    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService service) {
        this.service = service;
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return service.getAll();
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam(name = "question") String question,
                                @RequestParam(name = "answer") String answer
    ) {
        return service.add(question, answer);
    }
    @GetMapping(path = "/remove")
    public Question removeQuestion(@RequestParam(name = "question") String question,
                                @RequestParam(name = "answer") String answer
    ) {
        return service.remove(new Question(question, answer));
    }

}
