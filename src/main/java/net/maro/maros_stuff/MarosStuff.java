package net.maro.maros_stuff;

import com.mojang.logging.LogUtils;
import net.maro.maros_stuff.block.ModBlocks;
import net.maro.maros_stuff.item.ModCreativeModTabs;
import net.maro.maros_stuff.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MarosStuff.MOD_ID)
public class MarosStuff {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "maros_stuff";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MarosStuff(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus() ;

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SOCK);
            event.accept(ModItems.REDSTONE_ALLOY_INGOT);
            event.accept(ModItems.DIAMONDED_QUARTZ);
            event.accept(ModItems.ENDERIC_ACID);
            event.accept(ModItems.ENDER_ALLOY_INGOT);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.REDSTONE_ALLOY_BLOCK);
            event.accept(ModBlocks.DIAMONDED_QUARTZ_BLOCK);
            event.accept(ModBlocks.ENDERIC_ACID_CHUNK);
            event.accept(ModBlocks.ENDER_ALLOY_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
