package pro.sky.course2.examinerservice.services;

import pro.sky.course2.examinerservice.models.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
