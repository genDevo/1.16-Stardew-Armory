package net.gendevo.stardewarmory.items.tools;

import net.gendevo.stardewarmory.data.capabilities.IridiumModeCapability;
import net.gendevo.stardewarmory.data.capabilities.IIridiumMode;
import net.gendevo.stardewarmory.util.KeybindSetup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class IridiumPick extends PickaxeItem {

    public IridiumPick(Tier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable net.minecraft.world.level.Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        stack.getCapability(IridiumModeCapability.IRIDIUM_CAPABILITY).ifPresent(h -> {
            if (h.isIridiumMode() ) {
                tooltip.add(new TranslatableComponent("tooltip.stardewarmory.iridium_pick_on"));
            } else {
                tooltip.add(new TranslatableComponent("tooltip.stardewarmory.iridium_pick_off"));
            }
        });
        if (!Objects.isNull(world)) {
            tooltip.add(new TextComponent(new TranslatableComponent("tooltip.stardewarmory.press").getString() +
                    KeybindSetup.iridiumKey.getKey().getName().replaceAll("key.keyboard.", "").toUpperCase() +
                    new TranslatableComponent("tooltip.stardewarmory.toggle").getString()));
        }
    }

    @Nullable
    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        IIridiumMode cap = stack.getCapability(IridiumModeCapability.IRIDIUM_CAPABILITY).orElseThrow(() ->
                new IllegalArgumentException("Capability was empty on get, oh no!"));

        tag.putBoolean("SAnfo", cap.isIridiumMode());
        System.out.println(cap.isIridiumMode());
        return tag;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag tag) {
        super.readShareTag(stack, tag);

        if (tag != null) {
            IIridiumMode cap = stack.getCapability(IridiumModeCapability.IRIDIUM_CAPABILITY, null).orElseThrow(() ->
                    new IllegalArgumentException("Capability was empty on read, oh no!"));
            cap.setIridiumMode(tag.getBoolean("SAnfo"));
            System.out.println(cap.isIridiumMode());
        }
    }
}
