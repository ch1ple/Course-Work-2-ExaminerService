package pro.sky.course2.examinerservice.repository;

import pro.sky.course2.examinerservice.models.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    int size();

}
