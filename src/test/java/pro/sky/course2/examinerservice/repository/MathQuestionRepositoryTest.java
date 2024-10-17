package pro.sky.course2.examinerservice.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.sky.course2.examinerservice.exceptions.QuestionAlreadyExistsException;
import pro.sky.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.repository.impl.MathQuestionRepository;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static pro.sky.course2.examinerservice.services.constants.QuestionConstants.*;

class MathQuestionRepositoryTest {

    private final MathQuestionRepository out = new MathQuestionRepository();

    @BeforeEach
    void setUp() {
        out.add(MATH_QUESTION1);
        out.add(MATH_QUESTION2);
        out.add(MATH_QUESTION3);
        out.add(MATH_QUESTION4);
        out.add(MATH_QUESTION5);
    }

    @AfterEach
    void afterEach() {
        new HashSet<>(out.getAll()).forEach(out::remove);
    }

    @Test
    void initTest() {
        assertThat(out.getAll()).isEqualTo(MATH_QUESTIONS);
    }

    @Test
    void addTest() {
        int beforeAdd = out.getAll().size();

        Question expected = new Question(NEW_MATH_QUESTION, NEW_MATH_ANSWER);

        assertThat(out.add(NEW_MATH_QUESTION_OBJ))
                .isEqualTo(expected)
                .isIn(out.getAll());

        assertThat(out.getAll().size()).isEqualTo(beforeAdd + 1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при добавлении существующего вопроса-ответа")
    void addNegativeTest() {
        Question expected = new Question(MATH_QUESTION_1, MATH_ANSWER_1);

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> out.add(expected));
    }

    @Test
    @DisplayName("Должен добавить вопрос-ответ, переданные в виде нового объекта")
    void addQuestionTest() {
        int beforeAdd = out.getAll().size();

        Question expected = NEW_MATH_QUESTION_OBJ;

        assertThat(out.add(expected))
                .isEqualTo(expected)
                .isIn(out.getAll());

        assertThat(out.getAll().size()).isEqualTo(beforeAdd + 1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при добавлении вопроса-ответа, переданных в виде нового объекта")
    void addNegativeQuestionTest() {
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> out.add(new Question(MATH_QUESTION_1, MATH_ANSWER_1)));
    }

    @Test
    void removeTest() {
        int beforeRemove = out.getAll().size();

        Question expected = new Question(MATH_QUESTION_1, MATH_ANSWER_1);

        assertThat(out.remove(expected))
                .isEqualTo(expected)
                .isNotIn(out.getAll());

        assertThat(out.getAll().size()).isEqualTo(beforeRemove - 1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при удалении вопроса-ответа")
    void removeNegativeTest() {
        Question expected = new Question(NEW_MATH_QUESTION, NEW_MATH_ANSWER);

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> out.remove(expected));
    }

    @Test
    void getAllTest() {
        assertThat(out.getAll().size())
                .isEqualTo(5)
                .isSameAs(MATH_QUESTIONS.size());

        assertThat(out.getAll()).isEqualTo(MATH_QUESTIONS);
    }

    @Test
    void getAllNotNullTest() {
        assertThat(out.getAll())
                .isNotNull();
    }

}