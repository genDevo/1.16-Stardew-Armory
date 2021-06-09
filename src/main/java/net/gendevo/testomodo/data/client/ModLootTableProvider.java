package net.gendevo.testomodo.data.client;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.gendevo.testomodo.data.loot.ModChestLootTables;
import net.gendevo.testomodo.data.loot.ModEntityLootTables;
import net.gendevo.testomodo.setup.ModBlocks;
import net.gendevo.testomodo.setup.ModItems;
import net.gendevo.testomodo.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK),
                Pair.of(ModEntityLootTables::new, LootParameterSets.ENTITY),
                Pair.of(ModChestLootTables::new, LootParameterSets.CHEST)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
    }

    public static class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            dropSelf(ModBlocks.PRISMATIC_BLOCK.get());
            add(ModBlocks.PRISMATIC_ORE.get(), (p_218464_0_) -> {
                return createOreDrop(p_218464_0_, ModItems.PRISMATIC_SHARD.get());
            });

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
