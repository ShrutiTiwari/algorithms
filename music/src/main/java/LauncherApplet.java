import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.aqua.music.ui.GuiMultitabPanel;
import com.aqua.music.ui.SwingGuiLauncher;

public class LauncherApplet extends JApplet {
	public void init() {
		try {
			//SwingGuiLauncher.main(null);
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					//JLabel lab = new JLabel("Hello World, Shruti");
					//add(new GuiMultitabPanel(), BorderLayout.CENTER );
					JFrame jframe = new SwingGuiLauncher().createAndShowGUI();
					//add(jframe);
					setContentPane(new GuiMultitabPanel());
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI failed");
		}
	}
}
