package de.dafuqs.additionalentityattributes;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;

@EventBusSubscriber(modid = AdditionalEntityAttributes.MOD_ID)
public class AdditionalEntityAttributesNeoForge {
    /*
     * We run the init method in RegisterEvent so we can register before freeze.
     */
    @SubscribeEvent
    public static void registerContent(RegisterEvent event) {
        // Let's not run the init method multiple times.
        if (event.getRegistryKey() == Registries.ATTRIBUTE) {
            AdditionalEntityAttributes.init();
        }
    }

    @SubscribeEvent
    public static void modifyAttributes(EntityAttributeModificationEvent event) {
        for (EntityType<? extends LivingEntity> type : event.getTypes()) {
            event.add(type, AdditionalEntityAttributes.WATER_SPEED);
            event.add(type, AdditionalEntityAttributes.LAVA_SPEED);
            event.add(type, AdditionalEntityAttributes.WIDTH);
            event.add(type, AdditionalEntityAttributes.HEIGHT);
            event.add(type, AdditionalEntityAttributes.HITBOX_SCALE);
            event.add(type, AdditionalEntityAttributes.HITBOX_WIDTH);
            event.add(type, AdditionalEntityAttributes.HITBOX_HEIGHT);
            event.add(type, AdditionalEntityAttributes.MODEL_SCALE);
            event.add(type, AdditionalEntityAttributes.MODEL_WIDTH);
            event.add(type, AdditionalEntityAttributes.MODEL_HEIGHT);
            event.add(type, AdditionalEntityAttributes.MOB_DETECTION_RANGE);
            event.add(type, AdditionalEntityAttributes.MAGIC_PROTECTION);
        }
        event.add(EntityTypes.PLAYER, AdditionalEntityAttributes.WATER_VISIBILITY);
        event.add(EntityTypes.PLAYER, AdditionalEntityAttributes.LAVA_VISIBILITY);
        event.add(EntityTypes.PLAYER, AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE);
        event.add(EntityTypes.PLAYER, AdditionalEntityAttributes.BONUS_LOOT_COUNT_ROLLS);
        event.add(EntityTypes.PLAYER, AdditionalEntityAttributes.BONUS_RARE_LOOT_ROLLS);
        event.add(EntityTypes.PLAYER, AdditionalEntityAttributes.DROPPED_EXPERIENCE);
        event.add(EntityTypes.PLAYER, AdditionalEntityAttributes.COLLECTION_RANGE);
    }
}