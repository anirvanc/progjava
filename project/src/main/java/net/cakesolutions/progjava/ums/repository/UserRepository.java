package net.cakesolutions.progjava.ums.repository;

import net.cakesolutions.progjava.ums.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author anirvanchakraborty
 */
public interface UserRepository extends GraphRepository<User> {

	User findByUsername(String username);

    Page<User> findByFullNameLike(String fullName, Pageable page);

}
