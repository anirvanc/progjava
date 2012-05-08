package net.cakesolutions.progjava.ums.domain;

import net.cakesolutions.progjava.ums.repository.DepartmentRepository;
import net.cakesolutions.progjava.ums.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Neo4jOperations template;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public DepartmentRepository departmentRepository;

    // -- Tests for 'User' -- //
	@Test
	public void canFindUserByUsername() {
		User user = this.template.save(new User("test", "test user"));

		User userFromDb = this.userRepository.findByUsername("test");

		assertEquals("Created users is not the same as the looked up user", user, userFromDb);
	}

    @Test
    public void canFindAllUsers() {
        this.template.save(new User("test1", "test user 1"));
        this.template.save(new User("test2", "test user 2"));

        Page<User> users = this.userRepository.findAll(new PageRequest(0, 10));
        assertTrue("Expected 2 users but got " + users.getContent().size(), users.getContent().size() == 2);
    }

    @Test
    public void canFindByFullNameLike() {
        this.template.save(new User("test1", "test user 1"));
        this.template.save(new User("test2", "test user 2"));

        Page<User> users = this.userRepository.findByFullNameLike("1", new PageRequest(0, 10));
        assertTrue("Expected 1 user but got " + users.getContent().size(), users.getContent().size() == 1);
    }

	@Test
	public void userCanWorkAtADepartment() {
		User user = this.template.save(new User("test", "test user"));
		Department department = template.save(new Department("test department"));

		user.setDepartment(department);
		this.template.save(user);

		User userFromDb = this.userRepository.findByUsername("test");
		assertEquals("User doesn't work at correct department", userFromDb.getDepartment(), department);
	}

	@Test
	public void userCanHaveASupervisor() {
		User user = this.template.save(new User("test", "test user"));
		User supervisor = this.template.save(new User("supervisor", "supervisor user"));

		user.addSupervisor(supervisor);
		this.template.save(user);

		User userFromDb = this.userRepository.findByUsername("test");
		User supervisorFromDb = this.userRepository.findByUsername("supervisor");
		assertTrue("User is not supervised by the correct supervisor", userFromDb.isSupervisedBy(supervisorFromDb));
		assertTrue("The supervisor doesn't supervise the user", supervisorFromDb.isSupervisorOf(userFromDb));
	}

    @Test
    public void canDeleteUser() {
        User user = this.template.save(new User("test", "test user"));
        this.userRepository.delete(user.getId());

        User userFromDb = this.userRepository.findByPropertyValue("nodeId", user.getId());
        assertNull("User not deleted properly!", userFromDb);
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

    @Test
    public void canFindDepartmentByName() {
        Department department1 = this.template.save(new Department("test department"));
        Department department2 = this.template.save(new Department("test"));

        Page<Department> departmentPage = this.departmentRepository.findByNameLike("test", new PageRequest(0, 10));
        assertTrue("Expected 2 departments but got " + departmentPage.getContent().size(), departmentPage.getContent().size() == 2);

        departmentPage = this.departmentRepository.findByNameLike("department", new PageRequest(0, 10));
        assertTrue("Expected 1 department but got " + departmentPage.getContent().size(), departmentPage.getContent().size() == 1);
    }
}
