package net.maro.maros_stuff;

import com.mojang.logging.LogUtils;
import net.maro.maros_stuff.block.ModBlocks;
import net.maro.maros_stuff.block.entity.ModBlockEntities;
import net.maro.maros_stuff.item.ModCreativeModTabs;
import net.maro.maros_stuff.item.ModItems;
import net.maro.maros_stuff.screen.AlloyerMk1Screen;
import net.maro.maros_stuff.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
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

@Mod(MarosStuff.MOD_ID)
public class MarosStuff {
    public static final String MOD_ID = "maros_stuff";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MarosStuff(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus() ;

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

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
            event.accept(ModBlocks.ENDER_ALLOY_BLOCK);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            MenuScreens.register(ModMenuTypes.ALLOYER_MK1_MENU.get(), AlloyerMk1Screen::new);

        }
    }
}
