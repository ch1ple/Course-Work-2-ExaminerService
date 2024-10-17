package pro.sky.course2.examinerservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.repository.QuestionRepository;

@Service
public class MathQuestionService extends AbstractService {

    @Autowired
    public MathQuestionService(@Qualifier("mathQuestionRepository") final QuestionRepository questionRepository) {
        super(questionRepository);
    }

}
