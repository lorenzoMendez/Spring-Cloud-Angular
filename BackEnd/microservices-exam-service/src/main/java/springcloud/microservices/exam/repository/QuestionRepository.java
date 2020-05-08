package springcloud.microservices.exam.repository;

import org.springframework.data.repository.CrudRepository;

import springcloud.microservices.exam.common.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
