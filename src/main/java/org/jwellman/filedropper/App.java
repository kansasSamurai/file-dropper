package org.jwellman.filedropper;

import org.jwellman.foundation.Foundation;
import org.jwellman.foundation.uContext;
import org.jwellman.foundation.extend.AbstractSimpleMain;
import org.jwellman.foundation.interfaces.uiThemeProvider;

/**
 * A simple "applet" (not in the web browser sense)
 * that lets users drop files from OS File Explorers
 * and "does something with them".
 *
 */
public class App extends AbstractSimpleMain implements uiThemeProvider {
	
    public static void main( String[] args ) {
        new App().startup(true, args);
    }
    
    private App startup(boolean asMainFrame, String[] args) {
        final uContext context = uContext.createContext();
        context.setThemeProvider(this);
        context.setDimension(85);
        context.setLookAndFeel(Foundation.LAF_JTATTOO);        

        // Step 1 - Initialize Swing
        final Foundation f = Foundation.init(context);

        // Step 2 - Create your UIs in JPanel(s)
        mainui = f.registerUI("viewer", new UserInterface());

        // Step 3 - Use Foundation to create your "window"; give it your UI.
        window = f.useWindow(mainui); // f.useWindow(mainui); f.useDesktop(mainui);

        // Step 3a (optional) - Customize your window
        window.setTitle("File Dropper"); 
        window.setResizable(true);
        window.setMaximizable(true);

        // Step 4 - Create data models, controllers, and other non-UI objects
        // n/a
        
        // Step 4a (optional)- Associate models with views
        // n/a

        // Step 5 - Display your User Interface
        f.showGUI(window);
        
    	return this;
    }

    @Override // uiThemeProvider
	public void doTheme() {
		// TODO Auto-generated method stub
		
	}
    
}
