package net.cakesolutions.progjava.ums.domain;

import net.cakesolutions.progjava.ums.repository.DepartmentRepository;
import net.cakesolutions.progjava.ums.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.*;

/**
 * @author anirvanchakraborty
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/ums-test-context.xml"})
@Transactional
public class DomainTest {

    @Autowired
    Neo4jTemplate template;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    // -- Tests for 'User' -- //
	@Test @Ignore
	public void canFindUserByUsername() {
	}

    @Test
    public void canFindAllUsers() {
        User user1 = this.userRepository.save(new User("test1", "test user 1"));
        User user2 = this.userRepository.save(new User("test2", "test user 2"));

        User user1FromDb = this.userRepository.findOne(user1.getNodeId());
        assertEquals("User1 loaded from the db is not the same as the saved User1", user1FromDb, user1);

        User user2FromDb = this.userRepository.findOne(user2.getNodeId());
        assertEquals("User2 loaded from the db is not the same as the saved User2", user1FromDb, user1);

        Page<User> all = this.userRepository.findAll(new PageRequest(0, 100));
        assertEquals("No of all users not matching with total users loaded.", all.getContent().size(), 2);
    }

    @Test
    public void canFindByFullNameLike() {
        this.template.save(new User("test1", "test user 1"));
        this.template.save(new User("test2", "test user 2"));

        Page<User> users = this.userRepository.findByFullNameLike("1", new PageRequest(0, 10));
        assertTrue("Expected 1 user but got " + users.getContent().size(), users.getContent().size() == 1);
    }

	@Test @Ignore
	public void userCanWorkAtADepartment() {
	}

	@Test @Ignore
	public void userCanHaveASupervisor() {
	}

    @Test @Ignore
    public void canDeleteUser() {
    }

    // -- Tests for Department -- //
    @Test
    public void canSaveADepartment() {
        Department department = this.template.save(new Department("test department"));

        Department departmentFromDbByName = this.departmentRepository.findByName("test department");
        Department departmentFromDbById = this.departmentRepository.findOne(department.getNodeId());

        assertEquals("Created department is not equal to the looked up [by name] department", department, departmentFromDbByName);
        assertEquals("Created department is not equal to the looked up [by id] department", department, departmentFromDbById);
    }

    @Test @Ignore
    public void canFindDepartmentByName() {
    }
}
