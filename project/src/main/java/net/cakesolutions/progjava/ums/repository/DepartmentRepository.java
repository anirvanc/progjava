package net.cakesolutions.progjava.ums.repository;

import net.cakesolutions.progjava.ums.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author anirvanchakraborty
 */
public interface DepartmentRepository extends GraphRepository<Department> {

	Department findByName(String name);

    Page<Department> findByNameLike(String name, Pageable page);
}
