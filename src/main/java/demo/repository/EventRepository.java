package demo.repository;

import demo.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 

@RepositoryRestResource
public interface EventRepository extends CrudRepository<Event, String> {

}
