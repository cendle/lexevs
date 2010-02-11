package org.lexevs.dao.database.access.entity;

import java.util.List;

import org.LexGrid.commonTypes.Property;
import org.LexGrid.concepts.Entity;
import org.lexevs.dao.database.access.LexGridSchemaVersionAwareDao;


public interface EntityDao extends LexGridSchemaVersionAwareDao {

	public String insertEntity(String codingSchemeName, String version, Entity entity);
	
	public void insertBatchEntities(String codingSchemeId, List<? extends Entity> entities);
	
	public String getEntityId(String codingSchemeId, String entityCode, String entityCodeNamespace);
	
	public String insertEntity(String codingSchemeId, Entity entity);
	
	public String insertHistoryEntity(String codingSchemeId, Entity entity);
	
	public void updateEntity(String codingSchemeName, String version, Entity entity);
	
	
}
