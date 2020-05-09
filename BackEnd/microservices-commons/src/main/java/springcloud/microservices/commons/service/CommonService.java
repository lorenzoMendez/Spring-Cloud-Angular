package springcloud.microservices.commons.service;

import java.util.Optional;

public interface CommonService<E> {
	
	public Optional<E> findById( Long id  ) throws Exception;
	
	public Iterable<E> findAll();
	
	public E save( E entity );
	
	public void deleteById( Long id ) throws Exception;
}
