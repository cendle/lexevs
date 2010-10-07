package org.LexGrid.LexBIG.Impl.loaders.postprocessor;

import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.ExtensionDescription;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.LexBIG.Extensions.Generic.GenericExtension;
import org.LexGrid.LexBIG.Extensions.Load.OntologyFormat;
import org.LexGrid.LexBIG.Extensions.Load.postprocessor.LoaderPostProcessor;
import org.LexGrid.LexBIG.Impl.Extensions.AbstractExtendable;
import org.LexGrid.LexBIG.Impl.Extensions.ExtensionRegistryImpl;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.commonTypes.Properties;
import org.LexGrid.commonTypes.Property;
import org.LexGrid.commonTypes.Text;
import org.lexevs.dao.database.access.DaoManager;
import org.lexevs.dao.database.access.property.PropertyDao;
import org.lexevs.dao.database.access.property.PropertyDao.PropertyType;
import org.lexevs.dao.database.service.codingscheme.CodingSchemeService;
import org.lexevs.dao.database.service.daocallback.DaoCallbackService;
import org.lexevs.dao.database.service.daocallback.DaoCallbackService.DaoCallback;
import org.lexevs.dao.database.service.entity.EntityService;
import org.lexevs.locator.LexEvsServiceLocator;
import org.lexevs.logging.LoggerFactory;

public class OntologyFormatAddingPostProcessor extends AbstractExtendable implements LoaderPostProcessor {

    private static final long serialVersionUID = 2828520523031693573L;
    
    public static String EXTENSION_NAME = "OntologyFormatAddingPostProcessor";

    public void register() throws LBParameterException, LBException {
        ExtensionRegistryImpl.instance().registerGenericExtension(
                super.getExtensionDescription());
    }
 
    @Override
    protected ExtensionDescription buildExtensionDescription() {
        ExtensionDescription ed = new ExtensionDescription();
        ed.setDescription("OntologyFormatAddingPostProcessor");
        ed.setName(EXTENSION_NAME);
        ed.setExtensionBaseClass(GenericExtension.class.getName());
        ed.setExtensionClass(this.getClass().getName());
        
        return ed;
    }

    public void runPostProcess(AbsoluteCodingSchemeVersionReference reference, OntologyFormat ontFormat) {
        CodingSchemeService codingSchemeService = LexEvsServiceLocator.getInstance().getDatabaseServiceManager().getCodingSchemeService();
        DaoCallbackService daoCallbackService = LexEvsServiceLocator.getInstance().getDatabaseServiceManager().getDaoCallbackService();
        
        final String uri = reference.getCodingSchemeURN();
        final String version = reference.getCodingSchemeVersion();
        
        
        final CodingScheme codingScheme = codingSchemeService.getCodingSchemeByUriAndVersion(uri, version);
        
        if(codingScheme.getProperties() == null) {
            Properties properties = new Properties();
            codingScheme.setProperties(properties);
        }
        final Property prop = new Property();
        prop.setPropertyName(OntologyFormat.getMetaName());
        prop.setPropertyId(OntologyFormat.getMetaName());
        Text t = new Text();
        t.setContent(ontFormat.toString());
        prop.setValue(t);
        codingScheme.getProperties().addProperty(prop);
        
        try {
            daoCallbackService.executeInDaoLayer(new DaoCallback<Void>() {

                @Override
                public Void execute(DaoManager daoManager) {
                   String codingSchemeUid = daoManager.getCodingSchemeDao(uri, version).getCodingSchemeUIdByUriAndVersion(uri, version);
                   PropertyDao dao = daoManager.getPropertyDao(uri, version);
                   dao.insertProperty(codingSchemeUid, codingSchemeUid, PropertyType.CODINGSCHEME, prop);
                   return null;
                }
            });
        } catch (Exception e) {
           LoggerFactory.getLogger().warn("Post Process failed -- Load will not be rolled back.", e);
        }  
    }
}