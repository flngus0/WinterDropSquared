package snapshotcycle.wintersquared.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import snapshotcycle.wintersquared.WinterDropSquared;

public class ModModelLayerLocations {
    public static final ModelLayerLocation SHIVER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(WinterDropSquared.MOD_ID, "shiver"), "main");
}