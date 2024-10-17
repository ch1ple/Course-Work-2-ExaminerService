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
import pro.sky.course2.examinerservice.repository.impl.JavaQuestionRepository;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.course2.examinerservice.services.constants.QuestionConstants.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository repositoryMock;
    @InjectMocks
    private JavaQuestionService service;

    @Test
    @DisplayName("Должен добавить вопрос-ответ в виде объекта")
    void shouldAddQuestionAsObject() {
        when(repositoryMock.add(QUESTION1))
                .thenReturn(QUESTION1);
        Question actual = service.add(QUESTION1);
        Question expected = new Question(QUESTION_1, ANSWER_1);
        assertThat(actual)
                .isEqualTo(expected);
        verify(repositoryMock, times(1)).add(expected);
    }

    @Test
    @DisplayName("Должен добавить вопрос-ответ, переданные в виде строк")
    void ShouldAddQuestionAsString() {
        when(repositoryMock.add(NEW_QUESTION_OBJ))
                .thenReturn(NEW_QUESTION_OBJ);
        Question actual = service.add(NEW_QUESTION, NEW_ANSWER);
        Question expected = new Question(NEW_QUESTION, NEW_ANSWER);
        assertThat(actual)
                .isEqualTo(expected);
        verify(repositoryMock, times(1)).add(expected);
    }

    @Test
    @DisplayName("Должен удалить вопрос-ответ")
    void removeQuestionTest() {
        when(repositoryMock.remove(eq(QUESTION1)))
                .thenReturn(QUESTION1);
        assertEquals(QUESTION1, service.remove(QUESTION1));
        verify(repositoryMock, times(1)).remove(QUESTION1);
    }

    @Test
    @DisplayName("Должен выбросить исключение при удалении вопрос-ответа")
    void removeNegativeQuestionTest() {
        Question question = new Question(null, ANSWER_2);
        assertThatExceptionOfType(IncorrectQuestionOrAnswerException.class)
                .isThrownBy(() -> service.remove(question));
    }

    @Test
    @DisplayName("Должен выбросить исключение при удалении вопрос-ответа")
    void removeNegative2QuestionTest() {
        Question question = new Question(QUESTION_3, QUESTION_3);
        assertThatExceptionOfType(QuestionRepeatsAnswerException.class)
                .isThrownBy(() -> service.remove(question));
    }

    @Test
    @DisplayName("Должен получить все элементы коллекции")
    void getAllTest() {
        when(repositoryMock.getAll())
                .thenReturn(QUESTIONS);
        assertEquals(QUESTIONS, service.getAll());
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
        when(repositoryMock.getAll()).thenReturn(QUESTIONS);

        Question randomQuestion = service.getRandomQuestion();

        assertThat(randomQuestion)
                .isIn(QUESTIONS)
                .isNotNull();
    }

    @Test
    @DisplayName("Должен выбросить исключение при случайном вопросе")
    void getRandomQuestionNegativeTest() {
        assertThatExceptionOfType(QuestionsAreEmptyException.class)
                .isThrownBy(service::getRandomQuestion);
    }

    @Test
    public void shouldCallThrowExceptionInJavaQuestionServiceRemove() {
        Question remove = new Question(NEW_QUESTION, NEW_ANSWER);
        when(repositoryMock.remove(remove))
                .thenThrow(new QuestionNotFoundException());
        assertThrows(QuestionsAreEmptyException.class, () -> service.getRandomQuestion());
        assertThrows(QuestionNotFoundException.class, () -> service.remove(remove));
    }

}