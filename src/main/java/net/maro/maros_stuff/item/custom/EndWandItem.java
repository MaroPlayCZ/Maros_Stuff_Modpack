package net.maro.maros_stuff.item.custom;

import net.maro.maros_stuff.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EndWandItem extends Item {
    public EndWandItem(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        pPlayer.getCooldowns().addCooldown(this, 30);
        if (!pLevel.isClientSide) {
            ThrownEnderpearl thrownenderpearl = new ThrownEnderpearl(pLevel, pPlayer);
            thrownenderpearl.setItem(new ItemStack(Items.ENDER_PEARL));
            thrownenderpearl.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2F, 2F);
            pLevel.addFreshEntity(thrownenderpearl);
            pPlayer.getItemInHand(pHand).hurtAndBreak(1,pPlayer,player -> player.broadcastBreakEvent(pHand));
        }
        //replace on breaking
        if (itemstack.isEmpty()) {
            System.out.println("empty");
            itemstack = new ItemStack(ModItems.END_WAND_EMPTY.get());
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }
}
