package de.dafuqs.additionalentityattributes;

import net.minecraft.entity.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.util.*;

public class AdditionalEntityAttributesEntityTags {

	public static final TagKey<EntityType<?>> AFFECTED_BY_COLLECTION_RANGE = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(AdditionalEntityAttributes.MOD_ID,"affected_by_collection_range"));

}