package net.cakesolutions.progjava.ums.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.Set;

import static org.springframework.data.neo4j.support.index.IndexType.FULLTEXT;

/**
 * @author anirvanchakraborty
 */
@NodeEntity
public class User {
	@GraphId
    Long nodeId;
	@Indexed (unique = true)
    String username;
	@Indexed (indexType = FULLTEXT, indexName = "searchByFullName")
    String fullName;

	@RelatedTo(type="SUPERVISED_BY", direction = Direction.INCOMING)
	@Fetch Set<User> supervisors;

	@RelatedTo(type="SUPERVISED_BY", direction = Direction.OUTGOING)
	@Fetch Set<User> supervisedUsers;

	@RelatedTo(type="WORKS_AT", direction = Direction.OUTGOING)
	@Fetch Department department;

	public User() {
	}

	public User(String username, String fullName) {
		this.username = username;
		this.fullName = fullName;
	}

	public boolean isSupervisorOf(User user) {
		return this.supervisedUsers.contains(user);
	}

	public boolean isSupervisedBy(User user) {
		return this.supervisors.contains(user);
	}

	public void addSupervisor(User user) {
		this.supervisors.add(user);
		user.supervisedUsers.add(this);
    }

    public Long getId() {
        return this.nodeId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<User> getSupervisors() {
		return supervisors;
	}

	public Set<User> getSupervisedUsers() {
		return supervisedUsers;
	}

    public String getSupervisorFullNames() {
        if (this.supervisors.isEmpty()) return "--";
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        for (User supervisor : this.getSupervisors()) {
            sb.append(delimiter).append(supervisor.getFullName());
            delimiter = ", ";
        }
        return sb.toString();
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (!nodeId.equals(user.nodeId)) return false;
		if (!username.equals(user.username)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = nodeId.hashCode();
		result = 31 * result + username.hashCode();
		return result;
	}
}
