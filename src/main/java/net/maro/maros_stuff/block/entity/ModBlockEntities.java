package net.maro.maros_stuff.block.entity;

import net.maro.maros_stuff.MarosStuff;
import net.maro.maros_stuff.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MarosStuff.MOD_ID);

    public static final RegistryObject<BlockEntityType<AlloyerMk1BlockEntity>> ALLOYER_MK1_BE =
            BLOCK_ENTITIES.register("alloyer_mk1_be", () ->
                    BlockEntityType.Builder.of(AlloyerMk1BlockEntity::new,
                            ModBlocks.ALLOYER_MK1.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
