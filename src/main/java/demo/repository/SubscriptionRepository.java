package demo.repository;

import demo.model.Subscription;
import org.springframework.data.repository.CrudRepository;
 

 
public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

}
