package net.maro.maros_stuff.dataGen;

import net.maro.maros_stuff.MarosStuff;
import net.maro.maros_stuff.block.ModBlocks;
import net.maro.maros_stuff.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MarosStuff.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.REDSTONE_ALLOY_BLOCK.get(),
                        ModBlocks.DIAMONDED_QUARTZ_BLOCK.get(),
                        ModBlocks.ENDERIC_ACID_ORE.get(),
                        ModBlocks.ENDERIC_ACID_CHUNK.get(),
                        ModBlocks.ENDERIC_ACID_SMALL_BUD.get(),
                        ModBlocks.ENDERIC_ACID_MEDIUM_BUD.get(),
                        ModBlocks.ENDERIC_ACID_LARGE_BUD.get(),
                        ModBlocks.ENDERIC_ACID_CLUSTER.get(),
                        ModBlocks.ENDER_ALLOY_BLOCK.get());


        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.REDSTONE_ALLOY_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ENDERIC_ACID_CHUNK.get(),
                        ModBlocks.ENDERIC_ACID_CLUSTER.get());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.ENDER_ALLOY_BLOCK.get(),
                        ModBlocks.ENDERIC_ACID_ORE.get());

    }
}