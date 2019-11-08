package org.jwellman.filedropper.fileaction;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * This FileAction will MOVE the file to the destination directory
 * with a new name that includes a timestamp based on when the
 * DROP action occurred.  Though dependent on the wrapper logic,
 * the intent is that ALL files dropped onto the target will
 * get the same timestamp applied.
 * 
 * @author rwellman
 *
 */
public class FileActionMoveBasedOnDrop extends FileAction_Base {
	
	public FileActionMoveBasedOnDrop() {
		super();		
	}
	
	@Override
	public void doFileAction() {
		try {
			// Extract some source file info for later use...
			final String orgname = FilenameUtils.getBaseName(this.getSourceFile().getName());
			final String orgext = FilenameUtils.getExtension(this.getSourceFile().getName());
			
			// Build the destination file based on source file info...
			final String timestamp = FORMATTER.format(this.getActionTimestamp());			
			final File newfile = new File(this.getDestinationFile(), orgname + timestamp + "." + orgext);
			
			// Move the source file to the destination file...
			FileUtils.moveFile(this.getSourceFile(), newfile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
