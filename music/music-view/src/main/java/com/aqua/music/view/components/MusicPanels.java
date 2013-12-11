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
public enum MusicPanels {
	BOX_HORIZONTAL {
		@Override
		public JPanel createPanel() {
			JPanel result = new JPanel();
			result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
			return result;
		}
	},
	BOX_VERTICAL {
		@Override
		public JPanel createPanel() {
			JPanel result = new JPanel();
			result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));
			return result;
		}
	},
	LEFT_FLOWLAYOUT {
		@Override
		public JPanel createPanel() {
			return new JPanel(new FlowLayout(FlowLayout.LEFT));
		}
	},
	RIGHT_FLOWLAYOUT {
		@Override
		public JPanel createPanel() {
			return new JPanel(new FlowLayout(FlowLayout.RIGHT));
		}
	};
	public abstract JPanel createPanel();
}
