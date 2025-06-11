package net.maro.maros_stuff.block.entity;

import net.maro.maros_stuff.item.ModItems;
import net.maro.maros_stuff.screen.AlloyerMk1Menu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AlloyerMk1BlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4);

    private static final int IRON_INPUT = 0;
    private static final int COAL_INPUT = 1;
    private static final int REDSTONE_INPUT = 2;
    private static final int ALLOY_OUTPUT = 3;

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;

    public AlloyerMk1BlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ALLOYER_MK1_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AlloyerMk1BlockEntity.this.progress;
                    case 1 -> AlloyerMk1BlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AlloyerMk1BlockEntity.this.progress = pValue;
                    case 1 -> AlloyerMk1BlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer iventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            CompoundContainer inventory = null;
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, iventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.maros_stuff.alloyer_mk1");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AlloyerMk1Menu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("alloyer_mk1_progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("alloyer_mk1_progress");
        super.load(pTag);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.REDSTONE_ALLOY_INGOT.get(), 1);
        this.itemHandler.extractItem(IRON_INPUT, 1, false);
        this.itemHandler.extractItem(COAL_INPUT, 1, false);
        this.itemHandler.extractItem(REDSTONE_INPUT, 1, false);

        this.itemHandler.setStackInSlot(ALLOY_OUTPUT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(ALLOY_OUTPUT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = (this.itemHandler.getStackInSlot(IRON_INPUT).getItem() == Items.IRON_INGOT)&(this.itemHandler.getStackInSlot(COAL_INPUT).getItem() == Items.COAL)&(this.itemHandler.getStackInSlot(REDSTONE_INPUT).getItem() == Items.REDSTONE);
        ItemStack result = new ItemStack(ModItems.REDSTONE_ALLOY_INGOT.get());

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(ALLOY_OUTPUT).isEmpty() || this.itemHandler.getStackInSlot(ALLOY_OUTPUT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(ALLOY_OUTPUT).getCount() + count <= this.itemHandler.getStackInSlot(ALLOY_OUTPUT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }
}
