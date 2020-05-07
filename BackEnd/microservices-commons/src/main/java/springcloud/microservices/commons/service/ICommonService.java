package springcloud.microservices.commons.service;

import java.util.Optional;

public interface ICommonService<E> {
	
	public Optional<E> findById( Long id  ) throws Exception;
	
	public Iterable<E> findAll();
	
	public E save( E entity ) throws Exception;
	
	public void deleteById( Long id );
}
