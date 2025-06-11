package net.maro.maros_stuff.dataGen;

import net.maro.maros_stuff.MarosStuff;
import net.maro.maros_stuff.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MarosStuff.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.REDSTONE_ALLOY_BLOCK);
        blockWithItem(ModBlocks.DIAMONDED_QUARTZ_BLOCK);

        blockWithItem(ModBlocks.ENDERIC_ACID_ORE);
        blockWithItem(ModBlocks.ENDERIC_ACID_CHUNK);
        blockWithItem(ModBlocks.ENDER_ALLOY_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}