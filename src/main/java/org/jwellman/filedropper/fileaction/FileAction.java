package org.jwellman.filedropper.fileaction;

import java.io.File;
import java.time.LocalDateTime;

/**
 * An interface for file actions following the command pattern.
 * 
 * @author rwellman
 *
 */
public interface FileAction {

	public void doFileAction();

	public String getName();	
	public String getDescription();
	public File getSourceFile();
	public File getDestinationFile();
	public LocalDateTime getActionTimestamp();

	public void setName(String name);
	public void setDescription(String description);
	public void setSourceFile(File sourceFile);
	public void setDestinationFile(File destinationFile);
	public void setActionTimestamp(LocalDateTime actionTimestamp);

}
