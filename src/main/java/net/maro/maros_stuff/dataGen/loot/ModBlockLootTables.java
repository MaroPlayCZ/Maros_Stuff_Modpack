package net.maro.maros_stuff.dataGen.loot;

import net.maro.maros_stuff.block.ModBlocks;
import net.maro.maros_stuff.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.REDSTONE_ALLOY_BLOCK.get());
        this.dropSelf(ModBlocks.DIAMONDED_QUARTZ_BLOCK.get());
        this.dropSelf(ModBlocks.ENDER_ALLOY_BLOCK.get());
        this.dropSelf(ModBlocks.ALLOYER_MK1.get());

        this.dropOther(ModBlocks.ENDERIC_ACID_ORE.get(),ModItems.ENDERIC_ACID.get());
        this.dropOther(ModBlocks.ENDERIC_ACID_CLUSTER.get(),ModItems.ENDERIC_ACID.get());

        this.add(ModBlocks.ENDERIC_ACID_CHUNK.get(),
                block -> acidChunk(ModBlocks.ENDERIC_ACID_CHUNK.get(), ModItems.ENDERIC_ACID.get()));
    }

    protected LootTable.Builder acidChunk(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F)))));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}