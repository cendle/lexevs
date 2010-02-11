package org.lexevs.dao.database.ibatis;

import org.lexevs.dao.database.access.AbstractBaseDao;
import org.lexevs.dao.database.ibatis.batch.IbatisInserter;
import org.lexevs.dao.database.ibatis.batch.SqlMapClientTemplateInserter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public abstract class AbstractIbatisDao extends AbstractBaseDao implements InitializingBean {

	private SqlMapClientTemplate sqlMapClientTemplate;
	
	private IbatisInserter nonBatchTemplateInserter;
	
	public void afterPropertiesSet() throws Exception {
		setNonBatchTemplateInserter(new SqlMapClientTemplateInserter(this.getSqlMapClientTemplate()));
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setNonBatchTemplateInserter(IbatisInserter nonBatchTemplateInserter) {
		this.nonBatchTemplateInserter = nonBatchTemplateInserter;
	}

	public IbatisInserter getNonBatchTemplateInserter() {
		return nonBatchTemplateInserter;
	}
	

}
