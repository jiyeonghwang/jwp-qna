package qna.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDeletedFalse();

    List<Question> findByWriterId(User user);

    Optional<Question> findByIdAndDeletedFalse(Long id);
}
