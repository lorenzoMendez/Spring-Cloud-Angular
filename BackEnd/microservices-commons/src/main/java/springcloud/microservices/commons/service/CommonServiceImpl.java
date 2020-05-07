package springcloud.microservices.commons.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends CrudRepository<E, Long>> implements ICommonService<E> {
	
	// Protected para poderlo utilizar en clases hijas
	@Autowired
	protected R repository;

	@Override
	@Transactional( readOnly = true )
	public Optional<E> findById( Long id ) throws Exception {
		
		return repository.findById( id );
	}

	@Override
	@Transactional( readOnly = true )
	public Iterable<E> findAll() {
		
		return repository.findAll();
	}

	@Override
	@Transactional
	public E save(E entity ) throws Exception {
		
		return repository.save( entity );
	}

	@Override
	@Transactional
	public void deleteById( Long id ) {
		
		repository.deleteById( id );
	}
}
