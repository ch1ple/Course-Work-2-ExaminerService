package pro.sky.course2.examinerservice.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course2.examinerservice.exceptions.IncorrectQuestionOrAnswerException;
import pro.sky.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.course2.examinerservice.exceptions.QuestionRepeatsAnswerException;
import pro.sky.course2.examinerservice.exceptions.QuestionsAreEmptyException;
import pro.sky.course2.examinerservice.models.Question;
import pro.sky.course2.examinerservice.repository.impl.MathQuestionRepository;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static pro.sky.course2.examinerservice.services.constants.QuestionConstants.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private MathQuestionRepository repositoryMock;
    @InjectMocks
    private MathQuestionService service;

    @Test
    @DisplayName("Должен добавить вопрос-ответ в виде объекта")
    void shouldAddQuestionAsObject() {
        when(repositoryMock.add(MATH_QUESTION1))
                .thenReturn(MATH_QUESTION1);
        Question actual = service.add(MATH_QUESTION1);
        Question expected = new Question(MATH_QUESTION_1, MATH_ANSWER_1);
        assertThat(actual)
                .isEqualTo(expected);
        verify(repositoryMock, times(1)).add(expected);
    }

    @Test
    @DisplayName("Должен добавить вопрос-ответ, переданные в виде строк")
    void ShouldAddQuestionAsString() {
        when(repositoryMock.add(NEW_MATH_QUESTION_OBJ))
                .thenReturn(NEW_MATH_QUESTION_OBJ);
        Question actual = service.add(NEW_MATH_QUESTION, NEW_MATH_ANSWER);
        Question expected = new Question(NEW_MATH_QUESTION, NEW_MATH_ANSWER);
        assertThat(actual)
                .isEqualTo(expected);
        verify(repositoryMock, times(1)).add(expected);
    }

    @Test
    @DisplayName("Должен удалить вопрос-ответ")
    void removeQuestionTest() {
        when(repositoryMock.remove(eq(MATH_QUESTION1)))
                .thenReturn(MATH_QUESTION1);
        assertEquals(MATH_QUESTION1, service.remove(MATH_QUESTION1));
        verify(repositoryMock, times(1)).remove(MATH_QUESTION1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при удалении вопрос-ответа")
    void removeNegativeQuestionTest() {
        Question question = new Question(null, MATH_ANSWER_2);
        assertThatExceptionOfType(IncorrectQuestionOrAnswerException.class)
                .isThrownBy(() -> service.remove(question));
    }

    @Test
    @DisplayName("Должен выбросить исключение при удалении вопрос-ответа")
    void removeNegative2QuestionTest() {
        Question question = new Question(MATH_QUESTION_3, MATH_QUESTION_3);
        assertThatExceptionOfType(QuestionRepeatsAnswerException.class)
                .isThrownBy(() -> service.remove(question));
    }

    @Test
    @DisplayName("Должен получить все элементы коллекции")
    void getAllTest() {
        when(repositoryMock.getAll())
                .thenReturn(MATH_QUESTIONS);
        assertEquals(MATH_QUESTIONS, service.getAll());
    }

    @Test
    @DisplayName("Должен подтвердить, что коллекция пустая")
    void getAllTestWhenCollectionIsEmpty() {
        when(repositoryMock.getAll())
                .thenReturn(emptyList());
        assertTrue(service.getAll().isEmpty());
    }

    @Test
    @DisplayName("Должен получить случайный вопрос")
    void getRandomQuestionTest() {
        when(repositoryMock.size()).thenReturn(5);
        when(repositoryMock.getAll()).thenReturn(MATH_QUESTIONS);

        Question randomQuestion = service.getRandomQuestion();

        assertThat(randomQuestion)
                .isIn(MATH_QUESTIONS)
                .isNotNull();
    }

    @Test
    @DisplayName("Должен выбросить исключение при случайном вопросе")
    void getRandomQuestionNegativeTest() {
        assertThatExceptionOfType(QuestionsAreEmptyException.class)
                .isThrownBy(service::getRandomQuestion);
    }

    @Test
    public void shouldCallThrowExceptionInMathQuestionServiceRemove() {
        Question remove = new Question(NEW_MATH_QUESTION, NEW_MATH_ANSWER);
        when(repositoryMock.remove(remove))
                .thenThrow(new QuestionNotFoundException());
        assertThrows(QuestionsAreEmptyException.class, () -> service.getRandomQuestion());
        assertThrows(QuestionNotFoundException.class, () -> service.remove(remove));
    }

}