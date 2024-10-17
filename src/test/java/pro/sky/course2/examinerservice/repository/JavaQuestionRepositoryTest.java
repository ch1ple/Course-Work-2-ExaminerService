package pro.sky.course2.examinerservice.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.sky.course2.examinerservice.exceptions.QuestionAlreadyExistsException;
import pro.sky.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.repository.impl.JavaQuestionRepository;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static pro.sky.course2.examinerservice.services.constants.QuestionConstants.*;

class JavaQuestionRepositoryTest {

    private final JavaQuestionRepository out = new JavaQuestionRepository();

    @BeforeEach
    void setUp() {
        out.add(QUESTION1);
        out.add(QUESTION2);
        out.add(QUESTION3);
        out.add(QUESTION4);
        out.add(QUESTION5);
    }

    @AfterEach
    void afterEach() {
        new HashSet<>(out.getAll()).forEach(out::remove);
    }

    @Test
    void initTest() {
        assertThat(out.getAll()).isEqualTo(QUESTIONS);
    }

    @Test
    void addTest() {
        int beforeAdd = out.getAll().size();

        Question expected = new Question(NEW_QUESTION, NEW_ANSWER);

        assertThat(out.add(NEW_QUESTION_OBJ))
                .isEqualTo(expected)
                .isIn(out.getAll());

        assertThat(out.getAll().size()).isEqualTo(beforeAdd + 1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при добавлении существующего вопроса-ответа")
    void addNegativeTest() {
        Question expected = new Question(QUESTION_1, ANSWER_1);

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> out.add(expected));
    }

    @Test
    @DisplayName("Должен добавить вопрос-ответ, переданные в виде нового объекта")
    void addQuestionTest() {
        int beforeAdd = out.getAll().size();

        Question expected = NEW_QUESTION_OBJ;

        assertThat(out.add(expected))
                .isEqualTo(expected)
                .isIn(out.getAll());

        assertThat(out.getAll().size()).isEqualTo(beforeAdd + 1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при добавлении вопроса-ответа, переданных в виде нового объекта")
    void addNegativeQuestionTest() {
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> out.add(new Question(QUESTION_1, ANSWER_1)));
    }

    @Test
    void removeTest() {
        int beforeRemove = out.getAll().size();

        Question expected = new Question(QUESTION_1, ANSWER_1);

        assertThat(out.remove(expected))
                .isEqualTo(expected)
                .isNotIn(out.getAll());

        assertThat(out.getAll().size()).isEqualTo(beforeRemove - 1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при удалении вопроса-ответа")
    void removeNegativeTest() {
        Question expected = new Question(NEW_QUESTION, NEW_ANSWER);

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> out.remove(expected));
    }

    @Test
    void getAllTest() {
        assertThat(out.getAll().size())
                .isEqualTo(5)
                .isSameAs(QUESTIONS.size());

        assertThat(out.getAll()).isEqualTo(QUESTIONS);
    }

    @Test
    void getAllNotNullTest() {
        assertThat(out.getAll())
                .isNotNull();
    }

}