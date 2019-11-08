package org.jwellman.filedropper;

import com.jidesoft.swing.FolderChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import org.jwellman.filedropper.fileaction.FileAction;
import org.jwellman.filedropper.fileaction.FileActionListRenderer;
import org.jwellman.filedropper.fileaction.FileActionMoveBasedOnDrop;
import org.jwellman.filedropper.fileaction.FileActionMoveBasedOnFileMod;
import org.jwellman.swing.actions.FileActionAware;
import org.jwellman.swing.dnd.FileDropTarget;

/**
 * 
 * @author rwellman
 *
 */
@SuppressWarnings({"serial","unused"})
public class UserInterface extends JPanel implements FileActionAware {

	private JPanel dropTarget;
	private FolderChooser folderChooser;

	private JPanel actionContainer;
	private JScrollPane actionScroller;
	private FileAction selectedAction;
	private JList<FileAction> actionList;
	private DefaultListModel<FileAction> actionModel;
	
	public UserInterface() {
		super(new BorderLayout()); 

    	dropTarget = new JPanel(new GridBagLayout()); // (new GridBagLayout());
    	dropTarget.setBorder( BORDER_COMPOUND ); // BORDER_EMPTY ;
        final String calltoaction = "Drop File(s) Here";
        final Font font = new Font("Roboto", Font.PLAIN, 24);
        final Color foreground = new Color(0x92b0b3);
            final JLabel label = new JLabel(calltoaction);
            label.setBorder(BORDER_EMPTY);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setForeground(foreground);
            label.setFont((font));    	    
            dropTarget.add(label); // BorderLayout.CENTER

		folderChooser = new FolderChooser();
        folderChooser.setRecentListVisible(false);
        folderChooser.setNavigationFieldVisible(true);
        folderChooser.setControlButtonsAreShown(false);
        folderChooser.setFileSelectionMode(FolderChooser.DIRECTORIES_ONLY);
        folderChooser.setAvailableButtons(0);
//                FolderChooser.BUTTON_DESKTOP
//              + FolderChooser.BUTTON_MY_DOCUMENTS
//              + FolderChooser.BUTTON_REFRESH
//              + FolderChooser.BUTTON_NEW);
        
        new DropTarget(dropTarget, new FileDropTarget(dropTarget, this));
        
        actionModel = new DefaultListModel<>();

        // a reusable action reference... initialized with FileActionMoveBasedOnFileMod
        FileAction action = new FileActionMoveBasedOnDrop();
        action.setName("Move Based on Drop Time");
        action.setDescription("filename_YYYYMMDD_hhmm.ext"); // ("<html>filename<i>_YYYYMMDD_hhmm</i></html>");
        actionModel.addElement(action);    

        // The default selected action is this first action...
        selectedAction = action;

        // FileActionMoveBasedOnDrop ...
        action = new FileActionMoveBasedOnFileMod();        
        action.setName("Move Based on FileMod Time");
        action.setDescription("filename_YYYYMMDD_hhmm.ext"); // ("<html>filename<i>_YYYYMMDD_hhmm</i></html>");
        actionModel.addElement(action);        	
        
        // Create the JList...
        actionList = new JList<>(actionModel);
        actionList.setCellRenderer(new FileActionListRenderer());
        actionList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        // Put the JList in a scrollpane...
        actionScroller = new JScrollPane(actionList);
        actionScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        actionScroller.setBorder(null);
        
        // Put the scrollpane in a JPanel
        actionContainer = new JPanel(new BorderLayout());
        actionContainer.setBorder( BORDER_COMPOUND ); // BORDER_EMPTY ;
        actionContainer.add(actionScroller, BorderLayout.CENTER);
        
        // Add major containers to this JPanel... (try to keep this last)
        this.add(dropTarget, BorderLayout.CENTER);
        this.add(folderChooser, BorderLayout.WEST);
        this.add(actionContainer, BorderLayout.EAST);
        
	}
	
	public void doSingleFileAction(File file) {
		this.selectedAction.setActionTimestamp(LocalDateTime.now());
		this.singleFileAction(file);
		this.selectedAction.setActionTimestamp(null);
	}

	public void doListOfFilesAction(List<File> listoffiles) {
		System.out.println("N files were dropped... N=" + listoffiles.size());
		this.selectedAction.setActionTimestamp(LocalDateTime.now());
		for (File file : listoffiles) {
			this.singleFileAction(file);
		}
		this.selectedAction.setActionTimestamp(null);
	}

	private void singleFileAction(File file) {
    	final File s = folderChooser.getSelectedFolder();
    	if (s == null) {
    		throw new RuntimeException("SELECTED FOLDER is null");
    	} else {
            this.selectedAction.setDestinationFile(s);
    		this.selectedAction.setSourceFile(file);    		
    		this.selectedAction.doFileAction();		
    	}
	}
	
    private static final Border BORDER_EMPTY = BorderFactory.createEmptyBorder(5, 5, 5, 5);

	private static final Border BORDER_MATTE = BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(249,249,249));
    
    private static final Border BORDER_ETCHED = BorderFactory.createEtchedBorder();
    
    private static final Border BORDER_LINE = BorderFactory.createLineBorder(Color.black, 1);
    
    private static final Border BORDER_DEBUG = BorderFactory.createLineBorder(Color.red, 1);    
    
    private static final Border BORDER_DASHED = BorderFactory.createDashedBorder(null, 3.0f, 2.0f);
    
    private static final Border BORDER_COMPOUND = BorderFactory.createCompoundBorder(BORDER_EMPTY, BORDER_ETCHED);

}
