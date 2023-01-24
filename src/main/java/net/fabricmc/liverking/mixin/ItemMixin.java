package net.fabricmc.liverking.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.liverking.LiverKing;
import net.fabricmc.liverking.util.IEntityDataSaver;
import net.fabricmc.liverking.util.PrimalData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void finishUsingMixin(ItemStack stack, World world, LivingEntity entity, CallbackInfoReturnable<ItemStack> info) {
        if(!world.isClient() && stack.isFood() && entity instanceof PlayerEntity player) {
            String itemName = stack.getItem().toString();
            System.out.println(itemName);
            for(int i = 0; i < LiverKing.PrimalFoodNames.length; i++) {
                if(itemName.equals(LiverKing.PrimalFoodNames[i])) {
                    PrimalData.addPrimal((IEntityDataSaver) player, LiverKing.PrimalFoodEnergy[i]);
                }
            }
        }
    }
}
