package com.example.test6_gameframework;

public interface Music {
	public boolean isLooping() ;
	public boolean isPlaying() ;
	public boolean isStopped();
	public void play() ;
	public void setLooping(boolean isLooping);
	public void setVolume(float volume);
	public void stop();
}
