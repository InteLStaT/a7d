package org.ucoz.intelstat.a7d.servertest;

import java.util.HashMap;
import java.util.Map;

public class GameInfo {

	Map<String, Object> info;
	
	public GameInfo() {
		info = new HashMap<>();
	}
	
	public GameInfo(GameInfo other) {
		info = new HashMap<>(other.info);
	}
	
	void set(String key, Object value) {
		info.put(key, value);
	}
	
	<T> T get(String key) {
		return (T) info.get(key);
	}
	
	public byte[] encode() {
		
	}
	
	public static GameInfo decode(byte[] bytes) {
		
	}
}
