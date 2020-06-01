package xtvapps.simusplayer;

import java.io.IOException;

import fts.core.Application;
import fts.core.Context;
import fts.core.DesktopLogger;
import fts.core.DesktopResourceLocator;
import fts.core.Log;
import fts.core.SimpleCallback;
import fts.core.Widget;
import fts.core.Window;
import fts.linux.ComponentFactory;
import xtvapps.simusplayer.core.ModPlayer;
import xtvapps.simusplayer.core.ModPlayer.FrameInfo;
import xtvapps.simusplayer.core.ModPlayer.ModInfo;
import xtvapps.simusplayer.core.lcd.LcdScreenWidget;
import xtvapps.simusplayer.core.widgets.WaveContainer;
import xtvapps.simusplayer.core.widgets.WaveWidget;

public class SimusPlayerMod {
	private static final String LOGTAG = SimusPlayer.class.getSimpleName();
	private static final int SLEEP_TIME = 10;
	private static Window window;
	
	private static ModPlayer modPlayer;
	private static ModInfo modInfo;
	private static LcdScreenWidget lcdScreen;
	private static WaveContainer waveContainer;

	public static void main(String[] args) throws IOException {
		Application app = new Application(new ComponentFactory(), new DesktopResourceLocator(), new DesktopLogger(), new Context());
		
		window = Application.createWindow("Simus Mod Player", 640, 240);
		window.setOnFrameCallback(getOnFrameCallback());
		
		Widget rootView = app.inflate(window, "modplayer");
		window.setContentView(rootView);
		
		lcdScreen = (LcdScreenWidget)rootView.findWidget("lcd");
		waveContainer = (WaveContainer)rootView.findWidget("waves");

		modPlayer = new ModPlayer(new DesktopWaveDevice(44100, 1024));
		modPlayer.setModPlayerListener(new ModPlayer.ModPlayerListener() {
			
			@Override
			public void onStart() {
				modInfo = modPlayer.getModInfo();
				System.out.println("name: " + modInfo.modName);
				System.out.println("format: " + modInfo.modFormat);
				System.out.println("tracks: " + modInfo.tracks);
				System.out.println("patterns: " + modInfo.patterns);
				System.out.println("samples: " + modInfo.samples);
				System.out.println("speed: " + modInfo.speed);
				System.out.println("bpm: " + modInfo.bpm);
				for(int i=0; i<modInfo.samples; i++) {
					System.out.println(String.format("%02X : %s", i, modPlayer.xmpGetSampleName(i)));
				}
				lcdScreen.setName(toFirstLetterUppercase(modInfo.modName));
				waveContainer.setWaves(modInfo.tracks);
			}
			
			@Override
			public void onEnd() {
				Log.d(LOGTAG, "player ends");
			}
		});
		
		Thread t = new Thread() {
			public void run() {
				try {
					modPlayer.play("/home/fcatrin/tmp/mods/devlpr94.xm");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		t.start();
		
		window.open();
		window.mainLoop();
		modPlayer.stop();
	}

	private static long t0 = 0;
	protected static void onFrameCallback() {
		if (modInfo == null) return;

		for(int i=0; i<modInfo.tracks; i++) {
			waveContainer.setWave(i, modPlayer.getWave(i));
		}
		
		long t = System.currentTimeMillis();
		if (t - t0 > 200 ) {
			FrameInfo frameInfo = modPlayer.getFrameInfo();
			System.out.println("pos: " + frameInfo.position + "/" + modInfo.patterns + " spd:" + frameInfo.speed + " bpm:" + frameInfo.bpm);
			t0 = t;
		}
	}
	
	private static String toFirstLetterUppercase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	private static SimpleCallback getOnFrameCallback() {
		return new SimpleCallback() {
			
			@Override
			public void onResult() {
				onFrameCallback();
			}
		};
	}

}
