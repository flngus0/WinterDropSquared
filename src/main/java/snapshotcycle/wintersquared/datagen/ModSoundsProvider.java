package snapshotcycle.wintersquared.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import snapshotcycle.wintersquared.WinterDropSquared;
import snapshotcycle.wintersquared.init.ModSounds;

import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {
    public ModSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, SoundExporter soundExporter) {
        soundExporter.add(ModSounds.SHIVER_HURT, SoundTypeBuilder.of(ModSounds.SHIVER_HURT).subtitle("sounds.winter-squared.shiver_hurt")
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "mob/shiver/say1")))
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "mob/shiver/say2")))
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "mob/shiver/say3")))
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "mob/shiver/say4"))));

        soundExporter.add(ModSounds.SHIVER_DEATH, SoundTypeBuilder.of(ModSounds.SHIVER_DEATH).subtitle("sounds.winter-squared.shiver_death")
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "mob/shiver/death"))));
    }


    @Override
    public String getName() {
        return "";
    }
}
