/**
 * 
 */
package com.aqua.music.bo.audio.player;

import open.music.api.PlayApi.BasicNotePlayerBuidler;
import open.music.api.PlayApi.InitializationConfig;
import open.music.api.PlayApi.InitializationConfigImpl;
import open.music.api.PlayApi.InitializationConfigProvider;

/**
 * @author "Shruti Tiwari"
 *
 */
public interface AndroidConfigBuilder {
	BasicNotePlayerBuidler ANDROID= new  BasicNotePlayerBuidler() {
		@Override
		public BasicNotePlayer build() {
			return new BasicNotePlayerForAndroid();
		}
	};
	
	public enum AndroidConfig implements InitializationConfigProvider{
		DYNAMIC(new InitializationConfigImpl(AndroidConfigBuilder.ANDROID, AudioPlayer.Factory.DYNAMIC_AUDIO)),
		STATIC(new InitializationConfigImpl(AndroidConfigBuilder.ANDROID, AudioPlayer.Factory.STATIC_AUDIO));

		private InitializationConfig initializationConfig;
		private AndroidConfig(InitializationConfig initializationConfig) {
			this.initializationConfig = initializationConfig;
		}

		public InitializationConfig getConfig() {
			return initializationConfig;
		}
	}
}
