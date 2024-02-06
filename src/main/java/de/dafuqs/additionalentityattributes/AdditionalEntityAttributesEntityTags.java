package de.dafuqs.additionalentityattributes;

import net.minecraft.entity.*;
import net.minecraft.tag.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class AdditionalEntityAttributesEntityTags {

	public static final TagKey<EntityType<?>> AFFECTED_BY_COLLECTION_RANGE = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(AdditionalEntityAttributes.MOD_ID,"affected_by_collection_range"));

}