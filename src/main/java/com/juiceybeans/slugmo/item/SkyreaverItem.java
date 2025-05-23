package com.juiceybeans.slugmo.item;

import com.juiceybeans.slugmo.Slugmo;
import com.juiceybeans.slugmo.util.MobUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;

public class SkyreaverItem extends SwordItem {
    public SkyreaverItem() {
        super(ModToolTiers.VESPERTINE, 4, 2, new Item.Properties());
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (player.getY() >= 100) {
            entity.kill();
            MobUtils.spawnEntityAtPosition(new LightningBolt(EntityType.LIGHTNING_BOLT, entity.level()), entity.level(), entity.position());
            if (player.level().isClientSide) {
                player.displayClientMessage(Component.literal("The Wrath of Indra.").withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD), false);
            }

            Slugmo.LOGGER.debug("Tried to kill entity higher than y 100 using Skyreaver");
        } else {
            entity.hurt(entity.damageSources().playerAttack(player), 9);
            player.addDeltaMovement(new Vec3(0, 3, 0));
            entity.addDeltaMovement(new Vec3(0, 3, 0));
            Slugmo.LOGGER.debug("Tried to add 3 delta to player and entity");
        }
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return super.useOn(pContext);
    }
}
