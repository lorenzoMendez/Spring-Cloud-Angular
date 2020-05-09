package springcloud.microservices.commons.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {
	
	// Protected para poderlo utilizar en clases hijas
	@Autowired
	protected R repository;
	
	@Override
	@Transactional( readOnly = true )
	public Iterable<E> findAll() {
		return repository.findAll();
	}
	
	@Override
	@Transactional( readOnly = true )
	public Page<E> findAll( Pageable pageable ) {
		return repository.findAll( pageable );
	}
	
	@Override
	@Transactional( readOnly = true )
	public Optional<E> findById( Long id ) throws Exception {
		Optional<E> e = repository.findById( id );
		if( !e.isPresent() ) {
			throw new Exception( "No hay registro con el id " + id );
		}
		return e;
	}

	@Override
	@Transactional
	public E save(E entity ) {
		
		return repository.save( entity );
		
	}

	@Override
	@Transactional
	public void deleteById( Long id ) throws Exception {
		try {
			repository.deleteById( id );
		} catch( Exception err ) {
			throw new Exception( "Error! No se pudo borrar el registro." );
		}
	}
}
