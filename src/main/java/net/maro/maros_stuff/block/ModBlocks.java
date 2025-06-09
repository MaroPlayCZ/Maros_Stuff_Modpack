package net.maro.maros_stuff.block;

import net.maro.maros_stuff.MarosStuff;
import net.maro.maros_stuff.block.custom.AlloyerMk1Block;
import net.maro.maros_stuff.block.custom.EndericAcidChunkBlock;
import net.maro.maros_stuff.block.custom.EndericAcidClusterBlock;
import net.maro.maros_stuff.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MarosStuff.MOD_ID);

//add blocks here
    public static final RegistryObject<Block> REDSTONE_ALLOY_BLOCK = registerBlock("redstone_alloy_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> DIAMONDED_QUARTZ_BLOCK = registerBlock("diamonded_quartz_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));
    public static final RegistryObject<Block> ENDERIC_ACID_ORE = registerBlock("enderic_acid_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE)));
    public static final RegistryObject<Block> ENDERIC_ACID_CHUNK = registerBlock("enderic_acid_chunk",
            () -> new EndericAcidChunkBlock(BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST)));
    public static final RegistryObject<Block> ENDERIC_ACID_SMALL_BUD = registerBlock("enderic_acid_small_bud",
            () -> new EndericAcidClusterBlock(4,1, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noLootTable()));
    public static final RegistryObject<Block> ENDERIC_ACID_MEDIUM_BUD = registerBlock("enderic_acid_medium_bud",
            () -> new EndericAcidClusterBlock(6,1, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noLootTable()));
    public static final RegistryObject<Block> ENDERIC_ACID_LARGE_BUD = registerBlock("enderic_acid_large_bud",
            () -> new EndericAcidClusterBlock(8,1, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).noLootTable()));
    public static final RegistryObject<Block> ENDERIC_ACID_CLUSTER = registerBlock("enderic_acid_cluster",
            () -> new EndericAcidClusterBlock(12,1, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER)));
    public static final RegistryObject<Block> ENDER_ALLOY_BLOCK = registerBlock("ender_alloy_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> ALLOYER_MK1 = registerBlock("alloyer_mk1",
            () -> new AlloyerMk1Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
