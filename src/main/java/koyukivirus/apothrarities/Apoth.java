package koyukivirus.apothrarities;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import org.openjdk.nashorn.api.linker.NashornLinkerExporter;
import shadows.apotheosis.adventure.affix.reforging.ReforgingTableBlock;
import shadows.apotheosis.adventure.affix.reforging.ReforgingTableTile;
import shadows.apotheosis.adventure.affix.salvaging.SalvagingTableTile;
import shadows.apotheosis.adventure.boss.BossSpawnerBlock;
import shadows.apotheosis.ench.anvil.AnvilTile;
import shadows.apotheosis.ench.library.EnchLibraryTile;
import shadows.placebo.util.RegObjHelper;

public class Apoth {
    public static final RegObjHelper R = new RegObjHelper("apothrarities");
    public Apoth() {
    }

    public static final class Blocks {
        public static final RegistryObject<ReforgingTableBlock> ANCIENT_REFORGING_TABLE;
        public static final RegistryObject<ReforgingTableBlock> ESOTERIC_REFORGING_TABLE; ;

        public Blocks(){}

        static {
            ANCIENT_REFORGING_TABLE = R.block("ANCIENT_REFORGING_TABLE");
            ESOTERIC_REFORGING_TABLE = R.block("ESOTERIC_REFORGING_TABLE");
        }
    }
}
