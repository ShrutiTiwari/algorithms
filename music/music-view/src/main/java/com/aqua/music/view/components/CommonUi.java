/**
 * 
 */
package com.aqua.music.view.components;

import java.awt.Component;

import javax.swing.JPanel;

import open.music.api.DeviceType;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;

public class CommonUi<T>{
	private final StateDependentUiImpl stateDependentUi;
	private final JPanel bottomPanel;
	
	public CommonUi(){
		this.stateDependentUi = new StateDependentUiImpl();
		SingletonFactory.PLAY_API.initialize(stateDependentUi, DeviceType.DESKTOP_DYNAMIC);
		this.bottomPanel = new CommonUiBottom(stateDependentUi).panel();
	}
	
	public Component consoleArea() {
		return stateDependentUi.consoleArea();
	}

	public JPanel topPanel(){
		return stateDependentUi.topPanel();
	}
	
	public JPanel bottomPanel(){
		return bottomPanel;
	}
	
	public StateDependentUi stateDependentUi(){
		return stateDependentUi;
	}
}