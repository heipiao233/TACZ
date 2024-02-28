package com.tac.guns.client.sound;

import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;

public class GunSoundInstance extends EntityBoundSoundInstance {
    private final ResourceLocation registryName;

    public GunSoundInstance(SoundEvent soundEvent, SoundSource source, float volume, float pitch, Entity entity, ResourceLocation registryName) {
        super(soundEvent, source, volume, pitch, entity);
        this.registryName = registryName;
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }
}
