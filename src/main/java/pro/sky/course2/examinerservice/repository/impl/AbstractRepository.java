package pro.sky.course2.examinerservice.repository.impl;

import pro.sky.course2.examinerservice.exceptions.QuestionAlreadyExistsException;
import pro.sky.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.repository.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractRepository implements QuestionRepository {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(final String question, final String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(final Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistsException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(final Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public int size() {
        return questions.size();
    }

}
