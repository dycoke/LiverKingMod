package net.fabricmc.liverking.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.liverking.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootModifiers {
    private static final Identifier COW_ID = new Identifier("minecraft", "entities/cow");
    private static final Identifier PIG_ID = new Identifier("minecraft", "entities/pig");
    private static final Identifier CHICKEN_ID = new Identifier("minecraft", "entities/chicken");
    private static final Identifier SHEEP_ID = new Identifier("minecraft", "entities/sheep");
    private static final Identifier RABBIT_ID = new Identifier("minecraft", "entities/rabbit");

    private static final Identifier[] PRIMAL_ANIMALS = {COW_ID, PIG_ID, CHICKEN_ID, SHEEP_ID, RABBIT_ID};

    private static void AddOrgansLootTables(LootTable.Builder tableBuilder) {
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.35f))
                .with(ItemEntry.builder(ModItems.LIVER))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
        tableBuilder.pool(poolBuilder.build());
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.3f))
                .with(ItemEntry.builder(ModItems.STOMACH))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
        tableBuilder.pool(poolBuilder.build());
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.2f))
                .with(ItemEntry.builder(ModItems.BRAIN))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
        tableBuilder.pool(poolBuilder.build());
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.25f))
                .with(ItemEntry.builder(ModItems.KIDNEY))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
        tableBuilder.pool(poolBuilder.build());
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.1f))
                .with(ItemEntry.builder(ModItems.HEART))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
        tableBuilder.pool(poolBuilder.build());
    }

    public static void ModifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(source.isBuiltin()) {
                for(Identifier ANIMAL_ID : PRIMAL_ANIMALS) {
                    if(source.isBuiltin() && ANIMAL_ID.equals(id)) {
                        AddOrgansLootTables(tableBuilder);
                    }
                }
            }
        });
    }
}
