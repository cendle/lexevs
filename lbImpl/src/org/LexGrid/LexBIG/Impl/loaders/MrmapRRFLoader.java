package org.LexGrid.LexBIG.Impl.loaders;

import java.net.URI;

import org.LexGrid.LexBIG.DataModel.InterfaceElements.ExtensionDescription;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Extensions.Load.MrMap_Loader;
import org.LexGrid.LexBIG.Extensions.Load.options.OptionHolder;
import org.LexGrid.LexBIG.Utility.logging.CachingMessageDirectorIF;
import org.LexGrid.codingSchemes.CodingScheme;

import edu.mayo.informatics.lexgrid.convert.directConversions.MrmapToSQL;
import edu.mayo.informatics.lexgrid.convert.options.URIOption;
import edu.mayo.informatics.lexgrid.convert.utility.URNVersionPair;

public class MrmapRRFLoader extends BaseLoader implements MrMap_Loader{
    
    private static final long serialVersionUID = -689653003698478622L;
    
    public final static String name = "MrMap_Loader";
    
    private final static String description = "This loader loads MRMAP.RRF and MRSAT.RRF" +
    		" files into the LexGrid database as a mapping coding scheme.";

    public final static String VALIDATE = "Validate";
      public final static String MRSAT_URI = "MRSAT file path";
      public final static String MANIFEST_URI = "add'l Manifest";
      
    @SuppressWarnings("unused")
    private static boolean validate = true;

    public MrmapRRFLoader(){
        
        super();
    }


    @Override
    protected OptionHolder declareAllowedOptions(OptionHolder holder) {
    URIOption mrSatURI  = new URIOption(MRSAT_URI);
    holder.getURIOptions().add(mrSatURI);
        return holder;
    }

    @Override
    protected URNVersionPair[] doLoad() throws Exception {
        CachingMessageDirectorIF messages = getMessageDirector();
      MrmapToSQL map = new MrmapToSQL();
    if(getCodingSchemeManifest() != null){
        messages.warn("Pre-load of manifests is not supported in the MrMap Loader.  " +
        		"Manifest files can be post loaded instead");
    }
    if(getLoaderPreferences() != null){
        messages.warn("Loader Preferences are not supported in the MrMap Loader");
    }
     CodingScheme[] schemes = map.load(getMessageDirector(), 
              this.getResourceUri(), 
              this.getOptions().getURIOption(MRSAT_URI).getOptionValue(),
              null, null, null, null, null, null, null, null, null,
              this.getCodingSchemeManifest());
     setDoApplyPostLoadManifest(false);
     return this.constructVersionPairsFromCodingSchemes((Object[])schemes);
    }

    @Override
    protected ExtensionDescription buildExtensionDescription() {
        ExtensionDescription temp = new ExtensionDescription();
        temp.setExtensionBaseClass(MrmapRRFLoader.class.getInterfaces()[0].getName());
        temp.setExtensionClass(MrmapRRFLoader.class.getName());
        temp.setDescription(MrmapRRFLoader.description);
        temp.setName(MrmapRRFLoader.name);
        
        return temp;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    @Override
    public void load(URI mrMapsource, URI mrSatSource, String nameForMappingScheme, String nameForMappingVersion,
            String nameforMappingURI, String sourceScheme, String sourceVersion, String sourceURI, String targetScheme,
            String targetVersion, String targetURI, boolean stopOnErrors, boolean async) throws LBException{
        this.getOptions().getURIOption(MRSAT_URI).setOptionValue(mrSatSource);

        this.load(mrMapsource);
    }

    @Override
    public void validate(String source, int validationLevel) throws LBException {
        throw new UnsupportedOperationException();
    }

}
