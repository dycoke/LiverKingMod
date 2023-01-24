package net.fabricmc.liverking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.liverking.event.JoinServerEventHandler;
import net.fabricmc.liverking.item.ModItems;
import net.fabricmc.liverking.networking.ModMessages;
import net.fabricmc.liverking.util.ModLootModifiers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiverKing implements ModInitializer {
	public static final String MOD_ID = "liverking";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//primal energy bar
	public static final String[] PrimalFoodNames = {"steroids", "liver", "kidney", "brain", "stomach", "reconstructed_animal", "beef", "chicken", "rabbit", "mutton", "porkchop"};
	public static final int[] PrimalFoodEnergy = {5, 3, 3, 3, 3, 8, 1, 1, 1, 1, 1};


	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		ModMessages.registerS2CPackets();
		ModMessages.registerC2SPackets();

		ModLootModifiers.ModifyLootTables();
		LOGGER.info("Starting Mod: " + MOD_ID);

		ClientPlayConnectionEvents.JOIN.register(new JoinServerEventHandler());
	}
}
