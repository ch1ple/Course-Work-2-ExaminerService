package pro.sky.course2.examinerservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.course2.examinerservice.repository.QuestionRepository;

@Service
public class JavaQuestionService extends AbstractService {

    @Autowired
    public JavaQuestionService(@Qualifier("javaQuestionRepository") final QuestionRepository questionRepository) {
        super(questionRepository);
    }

}
