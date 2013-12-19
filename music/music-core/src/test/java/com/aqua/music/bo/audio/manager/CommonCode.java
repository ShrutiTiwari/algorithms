/**
 * 
 */
package com.aqua.music.bo.audio.manager;

import com.aqua.music.bo.audio.manager.CommonCode;

import open.music.api.DesktopConfig;
import open.music.api.SingletonFactory;
import open.music.api.StateDependentUi;

/**
 * @author "Shruti Tiwari"
 * 
 */
public class CommonCode {
	private static final StateDependentUi stateDependentUi=  new StateDependentUi(){
		@Override
		public void appendToConsole(String displayText) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setPauseToDisplay() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateConsole(String displayText) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateInstrument(String instrument) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updatePlayable(String playableName) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateTempo(int multipler) {
			// TODO Auto-generated method stub
			
		}
		
	};
	public static void initialize() {
		SingletonFactory.PLAY_API.initialize(stateDependentUi, DesktopConfig.DYNAMIC);
	}
	
	public static void staticInitialize() {
		SingletonFactory.PLAY_API.initialize(stateDependentUi, DesktopConfig.STATIC);
	}
}
