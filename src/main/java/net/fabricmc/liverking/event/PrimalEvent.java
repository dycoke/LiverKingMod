package net.fabricmc.liverking.event;

import java.util.Arrays;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface PrimalEvent {
    void onEat(ItemStack stack, PlayerEntity playerEntity);

    Event<PrimalEvent> EVENT = EventFactory.createArrayBacked(PrimalEvent.class, (listeners) -> 
        (stack, playerEntity) -> {
            Arrays.stream(listeners).forEach((listener) -> listener.onEat(stack, playerEntity));
        });
}
