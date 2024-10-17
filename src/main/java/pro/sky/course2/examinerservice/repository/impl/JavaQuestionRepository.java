package pro.sky.course2.examinerservice.repository.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class JavaQuestionRepository extends AbstractRepository {

    @PostConstruct
    public void init() {
        add("Вопрос по Java - 1", "Ответ по Java - 1");
        add("Вопрос по Java - 2", "Ответ по Java - 2");
        add("Вопрос по Java - 3", "Ответ по Java - 3");
        add("Вопрос по Java - 4", "Ответ по Java - 4");
        add("Вопрос по Java - 5", "Ответ по Java - 5");
    }

}
