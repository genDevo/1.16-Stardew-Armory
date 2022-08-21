package net.gendevo.stardewarmory.enchantments;

import net.gendevo.stardewarmory.config.StardewArmoryConfig;
import net.gendevo.stardewarmory.setup.ModSoundEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class CritEnchantment extends Enchantment {


    public CritEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slots) {
        super(rarity, type, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (!user.level.isClientSide()) {
            level = Math.min(level, 5);
            Random rand = new Random();
            int critChance = (int) MathHelper.clamp((9 - level), 0, 9);
            if (user.hasEffect(Effects.LUCK)) {
                critChance--;
            }
            int randomInt = rand.nextInt(critChance);
            if (randomInt == 1 && user instanceof PlayerEntity) {
                target.hurt(DamageSource.playerAttack((PlayerEntity) user), (8 + (level * 4)));
                if (StardewArmoryConfig.crit_sound.get()) {
                    user.level.playSound(null, user.xo, user.yo, user.zo, ModSoundEvents.LUCKY_HIT_SOUND.get(), SoundCategory.PLAYERS, 0.8f, 1);
                }
            }
        }
    }
}
