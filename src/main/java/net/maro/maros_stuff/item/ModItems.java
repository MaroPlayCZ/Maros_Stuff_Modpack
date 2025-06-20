package net.maro.maros_stuff.item;

import net.maro.maros_stuff.MarosStuff;
import net.maro.maros_stuff.item.custom.EndWandItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MarosStuff.MOD_ID);

//add items here
    public static final RegistryObject<Item> SOCK = ITEMS.register("sock",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MIATA = ITEMS.register("miata",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_ALLOY_INGOT = ITEMS.register("redstone_alloy_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMONDED_QUARTZ = ITEMS.register("diamonded_quartz",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDERIC_ACID = ITEMS.register("enderic_acid",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_ALLOY_INGOT = ITEMS.register("ender_alloy_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> END_WAND_EMPTY = ITEMS.register("end_wand_empty",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> END_WAND = ITEMS.register("end_wand",
            () -> new EndWandItem(new Item.Properties().defaultDurability(4)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
