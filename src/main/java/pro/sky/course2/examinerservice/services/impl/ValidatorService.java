package pro.sky.course2.examinerservice.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.exceptions.IncorrectQuestionOrAnswerException;
import pro.sky.course2.examinerservice.exceptions.QuestionRepeatsAnswerException;
import pro.sky.course2.examinerservice.models.Question;

@Service
public class ValidatorService {

    public Question checkQuestion(String question, String answer) {
        if (question == null || answer == null) {
            throw new IncorrectQuestionOrAnswerException();
        }
        if (question.equals(answer)) {
            throw new QuestionRepeatsAnswerException();
        }
        return new Question(question, answer);
    }

}
