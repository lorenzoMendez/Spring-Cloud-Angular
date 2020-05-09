package springcloud.microservices.exam.repository;

import org.springframework.data.repository.CrudRepository;

import springcloud.microservices.exam.common.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
