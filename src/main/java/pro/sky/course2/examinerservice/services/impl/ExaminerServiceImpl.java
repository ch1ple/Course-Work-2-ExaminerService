package pro.sky.course2.examinerservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.exceptions.NotEnoughQuestionsException;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.services.ExaminerService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;
    private final Random random;

    @Autowired
    public ExaminerServiceImpl(final JavaQuestionService javaQuestionService,
                               final MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }

    private int maxQuestionsNumber() {
        return mathQuestionService.size() + javaQuestionService.size();
    }

    private int randomGenerator() {
        return random.nextInt(2);
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > maxQuestionsNumber() || amount <= 0) {
            throw new NotEnoughQuestionsException();
        }

        Set<Question> result = new HashSet<>();

        while (result.size() < amount) {
            if (randomGenerator() > 0 && javaQuestionService.size() > 0) {
                result.add(javaQuestionService.getRandomQuestion());
            } else if (mathQuestionService.size() > 0) {
                result.add(mathQuestionService.getRandomQuestion());
            }
        }

        return result;
    }

}
