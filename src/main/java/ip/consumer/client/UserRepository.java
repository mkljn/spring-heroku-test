package ip.consumer.client;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<GeoDto, Integer> {

}
