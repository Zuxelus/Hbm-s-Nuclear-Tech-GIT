package com.hbm.handler;

import org.lwjgl.input.Keyboard;

import com.hbm.capability.HbmCapability;
import com.hbm.capability.HbmCapability.IHBMData;
import com.hbm.main.MainRegistry;
import com.hbm.packet.KeybindPacket;
import com.hbm.packet.PacketDispatcher;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HbmKeybinds {

	public static final String category = "key.categories.hbm";
	
	public static KeyBinding jetpackKey = new KeyBinding(category + ".toggleBack", Keyboard.KEY_C, category);
	public static KeyBinding hudKey = new KeyBinding(category + ".toggleHUD", Keyboard.KEY_V, category);
	public static KeyBinding reloadKey = new KeyBinding(category + ".reload", Keyboard.KEY_R, category);
	
	public static void register() {
		ClientRegistry.registerKeyBinding(jetpackKey);
		ClientRegistry.registerKeyBinding(hudKey);
		ClientRegistry.registerKeyBinding(reloadKey);
	}
	
	@SubscribeEvent
	public void keyEvent(KeyInputEvent event) {
		
		IHBMData props = HbmCapability.getData(MainRegistry.proxy.me());
		
		for(EnumKeybind key : EnumKeybind.values()) {
			boolean last = props.getKeyPressed(key);
			boolean current = MainRegistry.proxy.getIsKeyPressed(key);
			
			if(last != current) {
				PacketDispatcher.wrapper.sendToServer(new KeybindPacket(key, current));
				props.setKeyPressed(key, current);
			}
		}
	}
	
	public static enum EnumKeybind {
		JETPACK,
		TOGGLE_JETPACK,
		TOGGLE_HEAD,
		RELOAD
	}
}
