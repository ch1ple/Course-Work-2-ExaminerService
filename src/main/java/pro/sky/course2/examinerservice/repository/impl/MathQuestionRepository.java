package pro.sky.course2.examinerservice.repository.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;


@Repository
public class MathQuestionRepository extends AbstractRepository {

    @PostConstruct
    public void init() {
        add("2 * 3", "6");
        add("90 / 5", "18");
        add("2 ^ 4", "16");
        add("60 - 10", "50");
        add("55 + 55", "110");
        add("55 - 55", "0");
        add("5!", "120");
        add("8 * 8", "64");
        add("5 % 3", "2");
        add("log(2)", "8");
    }

}
