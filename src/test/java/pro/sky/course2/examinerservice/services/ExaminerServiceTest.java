package pro.sky.course2.examinerservice.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.course2.examinerservice.repository.impl.JavaQuestionRepository;
import pro.sky.course2.examinerservice.repository.impl.MathQuestionRepository;
import pro.sky.course2.examinerservice.services.impl.ExaminerServiceImpl;
import pro.sky.course2.examinerservice.services.impl.JavaQuestionService;
import pro.sky.course2.examinerservice.services.impl.MathQuestionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pro.sky.course2.examinerservice.services.constants.QuestionConstants.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceTest {

    @Mock
    private JavaQuestionService javaQuestionService = new JavaQuestionService(new JavaQuestionRepository());
    @Mock
    private MathQuestionService mathQuestionService = new MathQuestionService(new MathQuestionRepository());
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    @DisplayName("Должен подтвердить, что генерируется рандомный JAVA-вопрос")
    void containsJavaQuestionTest() {
        when(javaQuestionService.size()).thenReturn(5);
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(QUESTION1, QUESTION2, QUESTION3, QUESTION1, QUESTION4, QUESTION5);
        assertThat(examinerService.getQuestions(1))
                .contains(QUESTION1);
    }

    @Test
    @DisplayName("Должен подтвердить, что генерируется рандомный Math-вопрос")
    void containsMathQuestionTest() {
        when(mathQuestionService.size()).thenReturn(5);
        when(mathQuestionService.getRandomQuestion())
                .thenReturn(MATH_QUESTION1, MATH_QUESTION2, MATH_QUESTION3, MATH_QUESTION4, MATH_QUESTION5, MATH_QUESTION1);
        assertThat(examinerService.getQuestions(1))
                .contains(MATH_QUESTION1);
    }

}