package org.jwellman.filedropper.fileaction;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A base class for all FileAction(s).
 * 
 * Note that derived classes are intended to act as Singletons
 * (though the singleton probably won't be enforced by design) 
 * and be more of a "strategy" pattern to be plugged into an
 * instance of FileActionController and be re-usable by merely
 * updating its constituent properties and calling the "action" command.
 * 
 * @author rwellman
 *
 */
public class FileAction_Base implements FileAction {

	protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("_yyyyMMdd_hhmm");	

	// ALL file actions will require a source file -- if this should ever 
	// change, then I will document it here.
	private File sourceFile;
	
	// MOST (if not all) file actions will require a destination file
	private File destinationFile;
	
	// Some actions will want to know when the action occurs
	private LocalDateTime actionTimestamp;

	// name property (with a default)
	private String name = "action-name-goes-here";
	
	// description property (with a default)
	private String description = "action-description-goes-here";
	
	/**
	 * 
	 */
	public FileAction_Base() {
		// Intentionally Empty (for now... maybe common code later?)
	}

	/**
	 * A default implementation that merely "logs" the filenames to System.out.
	 * This implementation is obviously relatively useless but is present
	 * to provide default activity during development of new classes.
	 * 
	 * It would be expected that ALL subclasses of FileAction would override this method.
	 * 
	 */
	@Override
	public void doFileAction() {
		try {
			System.out.println("Srce. File: " + sourceFile.getCanonicalPath());
			
	        if (destinationFile != null) 
			System.out.println("Dest. Path: " + destinationFile.getCanonicalPath());	        	
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the sourceFile
	 */
	public File getSourceFile() {
		return sourceFile;
	}

	/**
	 * @param sourceFile the sourceFile to set
	 */
	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	/**
	 * @return the destinationFile
	 */
	public File getDestinationFile() {
		return destinationFile;
	}

	/**
	 * @param destinationFile the destinationFile to set
	 */
	public void setDestinationFile(File destinationFile) {
		this.destinationFile = destinationFile;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the actionTimestamp
	 */
	public LocalDateTime getActionTimestamp() {
		return actionTimestamp;
	}

	/**
	 * @param actionTimestamp the actionTimestamp to set
	 */
	public void setActionTimestamp(LocalDateTime actionTimestamp) {
		this.actionTimestamp = actionTimestamp;
	}

}
