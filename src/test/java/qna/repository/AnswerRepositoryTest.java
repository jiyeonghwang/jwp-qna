package qna.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import qna.domain.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
public class AnswerRepositoryTest {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    void findAll() {
        userRepository.save(UserTest.JAVAJIGI);
        questionRepository.save(QuestionTest.Q1);

        Answer expected = answerRepository.save(AnswerTest.A1);
        List<Answer> actual = answerRepository.findAll();
        assertAll(
                () -> assertThat(actual.get(0).getWriterId()).isEqualTo(expected.getWriterId()),
                () -> assertThat(actual.get(0).getCreatedAt()).isNotNull()
        );
    }

    @Test
    void findByIdAndDeletedFalse() {
        userRepository.save(UserTest.JAVAJIGI);
        questionRepository.save(QuestionTest.Q1);
        userRepository.save(UserTest.SANJIGI);
        questionRepository.save(QuestionTest.Q2);

        answerRepository.save(AnswerTest.A1);
        answerRepository.save(AnswerTest.A2);
        Answer answer = answerRepository.findByIdAndDeletedFalse(AnswerTest.A2.getId()).get();
        assertAll(
                () -> assertThat(answer).isNotNull(),
                () -> assertThat(answer.getId()).isNotEqualTo(AnswerTest.A1.getId()),
                () -> assertThat(answer.getContents()).isEqualTo(AnswerTest.A2.getContents())
        );
    }

    @Test
    void findByWriterIdAndQuestionId() {
        User user = userRepository.save(UserTest.JAVAJIGI);
        Question question = questionRepository.save(QuestionTest.Q1);
        Answer expected = answerRepository.save(AnswerTest.A1);
        List<Answer> actual = answerRepository.findByWriterIdAndQuestionId(user, question);
        assertThat(actual.get(0)).isEqualTo(expected);
    }
}
