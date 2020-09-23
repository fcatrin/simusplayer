package xtvapps.simusplayer.core.audio;

import xtvapps.simusplayer.core.audio.AudioBuffer.Format;
import xtvapps.simusplayer.core.audio.AudioBuffer.Status;

public class AudioRenderThread extends Thread {
	private AudioBuffer audioBuffers[];
	private long lastTime = 0;
	private int resolution = 0;
	private int currentBufferIndex = 0;
	private int nextBufferIndex = 0;
	
	private boolean running;
	
	private AudioRenderer audioRenderer;
	private AudioProcessor audioProcessor;

	private String audioRendererLock = "";
	
	public AudioRenderThread(int freq, int resolution, int buffers) {
		this.resolution = resolution;
		
		int bufferSize = freq / resolution;
		
		audioBuffers = new AudioBuffer[buffers];
		for(int i=0; i<buffers; i++) {
			audioBuffers[i] = new AudioBuffer(bufferSize, 0, Format.S16);
		}
	}
	
	public void setAudioRenderer(AudioRenderer audioRenderer) {
		synchronized (audioRendererLock) {
			this.audioRenderer = audioRenderer;
		}
	}
	
	public void render() {
		long t0 = System.currentTimeMillis();
		long waitmsec = lastTime + resolution - t0;
		if (waitmsec<=0) {
			renderBuffer();
		} else if (waitmsec > resolution / 2) {
			sleepms(resolution / 2);
		}
	}
	
	public void renderBuffer() {
		AudioBuffer audioBuffer = audioBuffers[currentBufferIndex];
		while (audioBuffer.getStatus() != Status.Free) sleepms(1);
		
		synchronized (audioRendererLock) {
			if (audioRenderer!=null) {
				audioRenderer.fillBuffer(audioBuffer.samplesIn);
				
			} else {
				sleepms(10);
				return;
			}
		}
		
		audioBuffer.render();
		if (audioProcessor != null) audioProcessor.process(audioBuffer);
		audioBuffer.setStatus(Status.Filled);
		
		currentBufferIndex = ++currentBufferIndex % audioBuffers.length;
	}

	private static void sleepms(long msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public AudioBuffer getNextBuffer() {
		AudioBuffer audioBuffer = audioBuffers[nextBufferIndex];
		if (audioBuffer.getStatus() != Status.Filled) return null;
		
		nextBufferIndex = ++nextBufferIndex % audioBuffers.length;
		return audioBuffer;
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			render();
		}
	}
	
	public void shutdown() {
		running = false;
	}
}