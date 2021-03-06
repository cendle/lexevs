/*
 * Copyright: (c) 2004-2010 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 * 
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * 		http://www.eclipse.org/legal/epl-v10.html
 * 
 */
package org.lexevs.exceptions;

import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.codingSchemes.CodingScheme;
import org.lexevs.dao.database.utility.DaoUtility;

/**
 * The Class CodingSchemeParameterException.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class CodingSchemeParameterException extends LBParameterException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -742865817743674751L;

	/**
	 * Instantiates a new coding scheme parameter exception.
	 * 
	 * @param ref the ref
	 * @param message the message
	 */
	public CodingSchemeParameterException(AbsoluteCodingSchemeVersionReference ref, String message) {
		super("Coding Scheme URI: " + ref.getCodingSchemeURN() + " Version: " + ref.getCodingSchemeVersion() + " - " + message);
	}
	
	/**
	 * Instantiates a new coding scheme parameter exception.
	 * 
	 * @param codingScheme the coding scheme
	 * @param message the message
	 */
	public CodingSchemeParameterException(CodingScheme codingScheme, String message) {
		this(DaoUtility.createAbsoluteCodingSchemeVersionReference(codingScheme.getCodingSchemeURI(), codingScheme.getRepresentsVersion()), message);
	}
}