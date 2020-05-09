package springcloud.microservices.answer.repository;

import org.springframework.data.repository.CrudRepository;

import springcloud.microservices.answer.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
