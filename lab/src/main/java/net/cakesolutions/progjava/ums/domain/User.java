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

    @RelatedTo(type = "WORKS_AT", direction = Direction.OUTGOING)
	@Fetch Department department;

    @RelatedTo(type="SUPERVISED_BY", direction = Direction.INCOMING)
    @Fetch Set<User> supervisors;

    @RelatedTo(type="SUPERVISED_BY", direction = Direction.OUTGOING)
    @Fetch Set<User> supervisedUsers;

	public User() {
	}

	public User(String username, String fullName) {
		this.username = username;
		this.fullName = fullName;
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
