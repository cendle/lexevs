/**
 * 
 */
package org.LexGrid.conceptdomain;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Impl.LexBIGServiceImpl;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceManager;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.concepts.Entity;
import org.junit.Test;
import org.lexgrid.conceptdomain.LexEVSConceptDomainServices;
import org.lexgrid.conceptdomain.impl.LexEVSConceptDomainServicesImpl;
import org.lexgrid.conceptdomain.util.ConceptDomainConstants;


/**
 * @author m004181
 *
 */
public class LexEVSConceptDomainServicesTest extends TestCase {

	private LexEVSConceptDomainServices cdServ_;
	private LexBIGService lbServ_;
	
	/**
	 * Test method for {@link org.lexgrid.conceptdomain.impl.LexEVSConceptDomainServicesImpl#getConceptDomainCodingScheme()}.
	 * @throws LBException 
	 */
	@Test
	public void testGetConceptDomainCodingScheme() throws LBException {
		CodingScheme cs = getConceptDomainService().getConceptDomainCodingScheme("", null);
		assertTrue(cs.getCodingSchemeURI().equals(ConceptDomainConstants.CONCEPT_DOMAIN_DEFAULT_CODING_SCHEME_URI));
		assertTrue(cs.getCodingSchemeName().equals(ConceptDomainConstants.CONCEPT_DOMAIN_DEFAULT_CODING_SCHEME_FORMAL_NAME));
		assertTrue(cs.getRepresentsVersion().equals(ConceptDomainConstants.CONCEPT_DOMAIN_DEFAULT_CODING_SCHEME_VERSION));		
	}

	/**
	 * Test method for {@link org.lexgrid.conceptdomain.impl.LexEVSConceptDomainServicesImpl#listAllConceptDomainEntities()}.
	 * @throws LBException 
	 */
	@Test
	public void testGetConceptDomainEntities() throws LBException {
		List<Entity> entities = getConceptDomainService().listAllConceptDomainEntities("", null);
		
		assertTrue(entities.size() == 2);
		for (Entity entity : entities)
		{
			assertTrue(entity.getEntityCode().equals("cd001") || entity.getEntityCode().equals("Autos"));
		}
	}

	/**
	 * Test method for {@link org.lexgrid.conceptdomain.impl.LexEVSConceptDomainServicesImpl#listAllConceptDomainIds()}.
	 * @throws LBException 
	 */
	@Test
	public void testGetConceptDomainIds() throws LBException {
		
		List<String> cdIds = getConceptDomainService().listAllConceptDomainIds("", null);
		for (String id : cdIds)
		{
			assertTrue(id.equals("cd001") || id.equals("Autos"));
		}
	}

	/**
	 * Test method for {@link org.lexgrid.conceptdomain.impl.LexEVSConceptDomainServicesImpl#getValueSetDefinitionURIsForConceptDomain(java.lang.String)}.
	 * @throws LBException 
	 */
	@Test
	public void testGetValueSetDefinitionURIsForConceptDomain() throws LBException {
		List<String> vsdURIS = getConceptDomainService().getConceptDomainBindings("Autos", null);
		for (String uri : vsdURIS)
		{
			System.out.println(uri);
		}
	}

	@Test
	public void testRemoveConceptDomainCodingSceheme() throws LBException{		
		LexBIGServiceManager lbsm = getLexBIGService().getServiceManager(null);
		AbsoluteCodingSchemeVersionReference acsvr = new AbsoluteCodingSchemeVersionReference();
		acsvr.setCodingSchemeURN(ConceptDomainConstants.CONCEPT_DOMAIN_DEFAULT_CODING_SCHEME_URI);
		acsvr.setCodingSchemeVersion(ConceptDomainConstants.CONCEPT_DOMAIN_DEFAULT_CODING_SCHEME_VERSION);
		lbsm.deactivateCodingSchemeVersion(acsvr, new Date());
		lbsm.removeCodingSchemeVersion(acsvr);
	}
	
	private LexEVSConceptDomainServices getConceptDomainService(){
		if (cdServ_ == null)
			cdServ_ = LexEVSConceptDomainServicesImpl.defaultInstance();
		
		return cdServ_;
	}
	
	private LexBIGService getLexBIGService(){
		if (lbServ_ == null)
			lbServ_ = LexBIGServiceImpl.defaultInstance();
		
		return lbServ_;
	}

}
