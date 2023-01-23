package net.fabricmc.liverking.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.liverking.LiverKing;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item STEROIDS = registerItem("steroids", new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(6f).alwaysEdible().snack().build())));
    public static final Item LIVER = registerItem("liver", new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(6).saturationModifier(15f).meat().build())));
    // public static final Item TESTICLES = registerItem("testicles", new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(14f).build())));
    public static final Item STOMACH = registerItem("stomach", new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(8).saturationModifier(16f).meat().build())));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(LiverKing.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LiverKing.LOGGER.debug("Registering Mod Items for " + LiverKing.MOD_ID);
    }
}
