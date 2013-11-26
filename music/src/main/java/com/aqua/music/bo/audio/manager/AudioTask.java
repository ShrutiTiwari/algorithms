package com.aqua.music.bo.audio.manager;

/**
 * @author "Shruti Tiwari"
 *
 * @param <T>
 */
public abstract class AudioTask<T> {
	public abstract void beforeForLoop();
	public abstract void forLoopBody(T t);
	public abstract T[] forLoopParameter();
}
