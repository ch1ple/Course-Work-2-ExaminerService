package pro.sky.course2.examinerservice.services.impl;

import pro.sky.course2.examinerservice.exceptions.QuestionsAreEmptyException;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.repository.QuestionRepository;
import pro.sky.course2.examinerservice.services.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public abstract class AbstractService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ValidatorService validatorService;
    private final Random random;

    public AbstractService(final QuestionRepository questionRepository
    ) {
        this.random = new Random();
        this.questionRepository = questionRepository;
        this.validatorService = new ValidatorService();
    }

    @Override
    public Question add(String question, String answer) {
        Question q = validatorService.checkQuestion(question, answer);
        return questionRepository.add(q);
    }

    @Override
    public Question add(Question question) {
        Question q = validatorService.checkQuestion(question.getQuestion(), question.getAnswer());
        return questionRepository.add(q);
    }

    @Override
    public Question remove(Question question) {
        Question q = validatorService.checkQuestion(question.getQuestion(), question.getAnswer());
        return questionRepository.remove(q);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionRepository.size() == 0) {
            throw new QuestionsAreEmptyException();
        }
        Collection<Question> questions = getAll();
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }

    @Override
    public int size() {
        return questionRepository.size();
    }

}
