package net.cakesolutions.progjava.ums.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import static org.springframework.data.neo4j.support.index.IndexType.FULLTEXT;

/**
 * @author anirvanchakraborty
 */
@NodeEntity
public class Department {
	@GraphId
    Long nodeId;
	@Indexed (indexType = FULLTEXT, indexName = "searchByName")
    String name;

	public Department() {
	}

	public Department(String name) {
		this.name = name;
	}

    public Long getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Department that = (Department) o;

		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (nodeId != null ? !nodeId.equals(that.nodeId) : that.nodeId != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = nodeId != null ? nodeId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
