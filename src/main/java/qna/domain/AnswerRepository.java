package qna.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionIdAndDeletedFalse(Long questionId);

    List<Answer> findByWriterIdAndQuestionId(User user, Question question);

    Optional<Answer> findByIdAndDeletedFalse(Long id);
}
