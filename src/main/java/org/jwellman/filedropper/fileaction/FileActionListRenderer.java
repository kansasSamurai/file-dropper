package org.jwellman.filedropper.fileaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class FileActionListRenderer extends JPanel implements ListCellRenderer<FileAction> {

	private JLabel lblName = new JLabel();
	private JLabel lblDescription = new JLabel();
	private JCheckBox chkSelected = new JCheckBox();
	private Font fontNormal = new Font("Corbel", Font.BOLD, 12); 
	// use Corbel, Candara, Segoe UI, Verdana (in order of preference)
	// fonts tried and rejected: Lucida Sans, Calibri*, Cambria, Constantia
	// * = acceptable at 14+ pt sizes; not at 12pt
	private Font fontItalic = new Font("Consolas", Font.PLAIN, 12); // Consolas
	
	public FileActionListRenderer() {
		super(new BorderLayout());
		
		Border inside = BorderFactory.createEmptyBorder(2, 0, 4, 27);
		Border outside = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
		
		this.setBorder(outside);
		this.setBackground(Color.white);
						
		lblName.setBorder(null);
		lblName.setFont(fontNormal);
		lblDescription.setBorder(null);		
		lblDescription.setFont(fontItalic); //lblDescription.getFont().deriveFont(Font.ITALIC));
		
		JPanel wrapper = new JPanel(new BorderLayout(5,5));
		wrapper.setBorder(inside);
		wrapper.setBackground(Color.white);		

		JPanel apanel = new JPanel(new GridLayout(0,1));
		apanel.setBackground(Color.white);
		apanel.add(lblName);
		apanel.add(lblDescription);
		wrapper.add(apanel, BorderLayout.CENTER);
		
		apanel = new JPanel(new BorderLayout());
		apanel.setBackground(Color.white);
		apanel.add(chkSelected, BorderLayout.NORTH);		
		wrapper.add(apanel, BorderLayout.WEST);
		
		this.add(wrapper, BorderLayout.CENTER);
	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends FileAction> list, FileAction value, int index, boolean isSelected, boolean cellHasFocus) {

		lblName.setText(value.getName());
		lblDescription.setText(value.getDescription());		
		chkSelected.setSelected(isSelected);
		
		if (isSelected) {
			chkSelected.setBackground(list.getSelectionBackground());
			//this.setBackground(list.getSelectionBackground());
		} else {
			chkSelected.setBackground(list.getBackground());
			//this.setBackground(list.getBackground());			
		}

		return this;
	}

}
