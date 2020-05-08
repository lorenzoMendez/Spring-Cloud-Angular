package springcloud.microservices.exam.repository;

import org.springframework.data.repository.CrudRepository;

import springcloud.microservices.exam.common.model.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long> {

}
