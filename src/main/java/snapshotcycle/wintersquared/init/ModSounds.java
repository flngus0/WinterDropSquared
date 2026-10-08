package snapshotcycle.wintersquared.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import snapshotcycle.wintersquared.WinterDropSquared;

public class ModSounds {
    public static final SoundEvent SHIVER_HURT = registerSoundEvent("shiver_hurt");
    public static final SoundEvent SHIVER_DEATH = registerSoundEvent("shiver_death");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void init() {
    }
}