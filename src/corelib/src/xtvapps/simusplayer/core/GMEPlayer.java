package xtvapps.simusplayer.core;

import java.io.File;

import fts.core.Log;
import xtvapps.simusplayer.core.audio.AudioRenderer;

public class GMEPlayer extends MediaPlayer {
	private static final String LOGTAG = GMEPlayer.class.getSimpleName();
	private static final int TIME_RWND_FWD = 5000;

	int handle;
	
	public GMEPlayer(WaveDevice waveDevice) {
		super(waveDevice);
	}

	@Override
	public void onInit() {}

	@Override
	public void onRelease() {}

	@Override
	public AudioRenderer onPrepare(File songFile) {
		handle = gmeOpen(songFile.getAbsolutePath(), waveDevice.getFreq());
		if (handle < 0) {
			Log.d(LOGTAG, "Cannot open fie " + songFile);
			return null;
		}
		
		gmeEnableAccuracy(handle, true);
		gmeSetStereoDepth(handle, 0.2);
		
		return new AudioRenderer() {
			
			@Override
			public void fillBuffer(byte[] buffer) {
				int result = gmeFillBuffer(handle, buffer);
				hasEnded = result < 0;
			}
		};
	}

	@Override
	public void onFinish() {
		gmeClose(handle);
		handle = -1;
	}

	@Override
	public void doForward() {
		move(TIME_RWND_FWD);
	}

	@Override
	public void doRewind() {
		move(-TIME_RWND_FWD);
	}
	
	private void move(int delta) {
		long msec = getTimeElapsed();
		long position = msec + delta;

		gmeSeek(handle, position);
	}

	@Override
	public int getTimeTotal() {
		return gmeTimeTotal(handle);
	}

	@Override
	public int getTimeElapsed() {
		return gmeTimeElapsed(handle);
	}

	@Override
	public void seek(long position) {
		gmeSeek(handle, position);
	}
	
	private static native int  gmeOpen(String path, int freq);
	private static native int  gmeFillBuffer(int handle, byte[] buffer);
	private static native void gmeSetStereoDepth(int handle, double depth);
	private static native void gmeEnableAccuracy(int handle, boolean enabled);
	private static native void gmeClose(int handle);
	private static native void gmeSeek(int handle, long position);
	private static native int  gmeTimeElapsed(int handle);
	private static native int  gmeTimeTotal(int handle);
}
