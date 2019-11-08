package org.jwellman.filedropper.fileaction;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * This FileAction will MOVE the file to the destination directory
 * with a new name that includes a timestamp based on when the
 * file was LAST MODIFIED.
 * 
 * TODO modify this class based on this blog entry
 * https://javarevisited.blogspot.com/2016/10/how-to-get-last-modified-date-of-file-and-directory-in-java.html
 * 
 * @author rwellman
 *
 */
public class FileActionMoveBasedOnFileMod extends FileAction_Base {

	public FileActionMoveBasedOnFileMod() {
		super();		
	}
	
	@Override
	public void doFileAction() {
		try {
			// Extract some source file info for later use...
			final String orgname = FilenameUtils.getBaseName(this.getSourceFile().getName());
			final String orgext = FilenameUtils.getExtension(this.getSourceFile().getName());
			final long modtime = this.getSourceFile().lastModified();
			
			// Build the destination file based on source file info...
			final LocalDateTime mod = LocalDateTime.ofInstant(Instant.ofEpochMilli(modtime), ZoneId.systemDefault());
			final String timestamp = FORMATTER.format(mod);			
			final File newfile = new File(this.getDestinationFile(), orgname + timestamp + "." + orgext);
			
			// Move the source file to the destination file...
			FileUtils.moveFile(this.getSourceFile(), newfile);
			
			// TODO handle the following exception... but probably through a global exception handler
			// org.apache.commons.io.FileExistsException: Destination 'C:\dev\working\batch\logs\temp\spring.log_20191030_1244.94' already exists
			// see: https://www.javamex.com/tutorials/exceptions/exceptions_uncaught_handler.shtml
			//      https://www.javamex.com/tutorials/exceptions/exceptions_uncaught_gui.shtml
			//      https://www.baeldung.com/java-global-exception-handler
			//      https://dzone.com/articles/exception-handling-in-real-life-applications
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
