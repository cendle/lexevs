package org.lexevs.registry.service;

import java.io.File;
import java.util.Date;

import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.DataModel.Core.types.CodingSchemeVersionStatus;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.lexevs.dao.database.access.registry.RegistryDao;
import org.lexevs.dao.database.connection.SQLConnectionInfo;
import org.lexevs.exceptions.InternalException;
import org.lexevs.registry.model.RegistryEntry;
import org.springframework.transaction.annotation.Transactional;

public class DatabaseRegistry implements Registry {
	
	private RegistryDao registryDao;

	@Transactional
	public void activate(AbsoluteCodingSchemeVersionReference codingScheme)
			throws LBInvocationException, LBParameterException {
		RegistryEntry entry = registryDao.getCodingSchemeEntryForUriAndVersion(
				codingScheme.getCodingSchemeURN(), 
				codingScheme.getCodingSchemeVersion());
		
		entry.setStatus(CodingSchemeVersionStatus.ACTIVE.toString());
		
		registryDao.updateRegistryEntry(entry);
	}

	public HistoryEntry addNewHistory(String urn, String dbURL, String dbName,
			String tablePrefix) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void addNewItem(String urn, String version, String status,
			String dbURL, String tag, String dbName, String tablePrefix)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void deactivate(DBEntry entry) throws LBInvocationException,
			LBParameterException {
		// TODO Auto-generated method stub
		
	}

	public DBEntry[] getDBEntries() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getDeactivateDate(String codingSchemeURN, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	public DBEntry getEntry(String codingSchemeURN, String version)
			throws LBParameterException {
		// TODO Auto-generated method stub
		return null;
	}

	public DBEntry getEntry(AbsoluteCodingSchemeVersionReference codingScheme)
			throws LBParameterException {
		// TODO Auto-generated method stub
		return null;
	}

	public HistoryEntry[] getHistoryEntries() {
		// TODO Auto-generated method stub
		return null;
	}

	public HistoryEntry getHistoryEntry(String urn) throws LBParameterException {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getLastUpdateDate(String codingSchemeURN, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Date getLastUpdateTime() {
		return this.registryDao.getLastUpdateTime();
	}

	public String getNextDBIdentifier() throws LBInvocationException {
		return this.registryDao.getLastUsedDbIdentifier();
	}

	public String getNextHistoryIdentifier() throws LBInvocationException {
		// TODO Auto-generated method stub
		return null;
	}

	public File getRegistryFile() {
		// TODO Auto-generated method stub
		return null;
	}

	public SQLConnectionInfo getSQLConnectionInfoForCodeSystem(
			AbsoluteCodingSchemeVersionReference codingSchemeVersion) {
		// TODO Auto-generated method stub
		return null;
	}

	public SQLConnectionInfo[] getSQLConnectionInfoForHistory(String urn) {
		// TODO Auto-generated method stub
		return null;
	}

	public CodingSchemeVersionStatus getStatus(String codingSchemeURN,
			String version) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTag(String codingSchemeURN, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVersionForTag(String urn, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isActive(String codingSchemeURN, String version) {
		// TODO Auto-generated method stub
		return false;
	}

	public void remove(AbsoluteCodingSchemeVersionReference codingSchemeVersion)
			throws InternalException, LBInvocationException,
			LBParameterException {
		// TODO Auto-generated method stub
		
	}

	public void removeHistoryEntry(String urn) throws InternalException,
			LBInvocationException, LBParameterException {
		// TODO Auto-generated method stub
		
	}

	public void setDeactivateDate(AbsoluteCodingSchemeVersionReference acsvr,
			Date date) throws LBParameterException, LBInvocationException {
		// TODO Auto-generated method stub
		
	}

	public void setStatusPending(DBEntry entry) throws LBInvocationException,
			LBParameterException {
		// TODO Auto-generated method stub
		
	}

	public void updateTag(AbsoluteCodingSchemeVersionReference codingScheme,
			String newTag) throws LBInvocationException, LBParameterException {
		// TODO Auto-generated method stub
		
	}

	public void updateURNVersion(
			AbsoluteCodingSchemeVersionReference oldURNVerison,
			AbsoluteCodingSchemeVersionReference newURNVerison)
			throws LBInvocationException, LBParameterException {
		// TODO Auto-generated method stub
		
	}

	public void updateVersion(
			AbsoluteCodingSchemeVersionReference codingScheme, String newVersion)
			throws LBInvocationException, LBParameterException {
		// TODO Auto-generated method stub
		
	}

	public void setRegistryDao(RegistryDao registryDao) {
		this.registryDao = registryDao;
	}

	public RegistryDao getRegistryDao() {
		return registryDao;
	}

}
