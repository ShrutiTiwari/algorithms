/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author "Shruti Tiwari"
 * 
 */
public enum UiJPanelBuilder {
	BOX_HORIZONTAL {
		@Override
		public JPanel createPanel() {
			JPanel result = new JPanel();
			result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
			result.setBackground(UiColor.BG_CLR);
			return result;
		}
	},
	BOX_VERTICAL {
		@Override
		public JPanel createPanel() {
			JPanel result = new JPanel();
			result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));
			result.setBackground(UiColor.BG_CLR);
			return result;
		}
	},
	LEFT_FLOWLAYOUT {
		@Override
		public JPanel createPanel() {
			JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));
			result.setBackground(UiColor.BG_CLR);
			return result;
		}
	},
	RIGHT_FLOWLAYOUT {
		@Override
		public JPanel createPanel() {
			JPanel result = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			result.setBackground(UiColor.BG_CLR);
			return result;
		}
	};
	public abstract JPanel createPanel();
}
