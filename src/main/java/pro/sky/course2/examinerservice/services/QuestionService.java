package pro.sky.course2.examinerservice.services;

import pro.sky.course2.examinerservice.models.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

    int size();

}
