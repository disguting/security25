package furman.security25.item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
@author   User
@project   security25
@class  ItemRepository
@version  1.0.0
@since 18.11.2025 - 06.13
*/
@Repository
public interface CatRepository extends MongoRepository<Cat, String> {
}
