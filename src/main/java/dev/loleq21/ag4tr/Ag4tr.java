package dev.loleq21.ag4tr;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import techreborn.api.events.CableElectrocutionEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ag4tr implements ModInitializer {

    public static final String MOD_ID = "ag4tr";
    public static final ItemGroup AG4TR_GROUP = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "items")).icon(() -> new ItemStack(Ag4trContent.HAZMAT_HELMET)).build();

    public static final EntityType[] bossMobsArray = new EntityType[] {EntityType.ENDER_DRAGON, EntityType.WITHER};
    public static final Set<EntityType> bossMobs = new HashSet<EntityType>(Arrays.asList(bossMobsArray));

    @Override
    public void onInitialize() {
        AutoConfig.register(Ag4trConfig.class, JanksonConfigSerializer::new);
        Ag4trContent.registerAg4trContent();

        CableElectrocutionEvent.EVENT.register((livingEntity, cableType, blockPos, world, cableBlockEntity) -> {
            if(livingEntity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) livingEntity;
                if (HazmatSuitUtils.playerIsWearingFullHazmat(player)) {
                    return false;
                }
            }
            return true;
        });
    }
}


