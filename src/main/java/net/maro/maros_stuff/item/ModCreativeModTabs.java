package net.maro.maros_stuff.item;

import net.maro.maros_stuff.MarosStuff;
import net.maro.maros_stuff.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MarosStuff.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAROS_STUFF =  CREATIVE_MODE_TABS.register("maros_stuff",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MIATA.get()))
                    .title(Component.translatable("creativetab.maros_stuff"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SOCK.get());
                        output.accept(ModItems.REDSTONE_ALLOY_INGOT.get());
                        output.accept(ModItems.DIAMONDED_QUARTZ.get());
                        output.accept(ModItems.ENDERIC_ACID.get());
                        output.accept(ModItems.ENDER_ALLOY_INGOT.get());
                        output.accept(ModItems.END_WAND.get());

                        output.accept(ModBlocks.REDSTONE_ALLOY_BLOCK.get());
                        output.accept(ModBlocks.DIAMONDED_QUARTZ_BLOCK.get());
                        output.accept(ModBlocks.ENDERIC_ACID_ORE.get());
                        output.accept(ModBlocks.ENDERIC_ACID_CHUNK.get());
                        output.accept(ModBlocks.ENDER_ALLOY_BLOCK.get());

                        output.accept(ModBlocks.ALLOYER_MK1.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
