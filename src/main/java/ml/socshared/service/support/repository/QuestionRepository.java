package ml.socshared.service.support.repository;

import ml.socshared.service.support.domain.db.QuestionDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionDB, Integer> {

}
