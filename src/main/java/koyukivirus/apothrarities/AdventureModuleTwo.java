package koyukivirus.apothrarities;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.types.Type;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shadows.apotheosis.Apoth;
import shadows.apotheosis.Apotheosis;
import shadows.apotheosis.adventure.AdventureConfig;
import shadows.apotheosis.adventure.AdventureEvents;
import shadows.apotheosis.adventure.AdventureGeneration;
import shadows.apotheosis.adventure.AdventureModule;
import shadows.apotheosis.adventure.affix.AffixManager;
import shadows.apotheosis.adventure.affix.reforging.ReforgingMenu;
import shadows.apotheosis.adventure.affix.reforging.ReforgingTableBlock;
import shadows.apotheosis.adventure.affix.reforging.ReforgingTableTile;
import shadows.apotheosis.adventure.affix.salvaging.SalvageItem;
import shadows.apotheosis.adventure.affix.salvaging.SalvagingMenu;
import shadows.apotheosis.adventure.affix.salvaging.SalvagingTableBlock;
import shadows.apotheosis.adventure.affix.salvaging.SalvagingTableTile;
import shadows.apotheosis.adventure.affix.socket.ExpulsionRecipe;
import shadows.apotheosis.adventure.affix.socket.ExtractionRecipe;
import shadows.apotheosis.adventure.affix.socket.SocketingRecipe;
import shadows.apotheosis.adventure.affix.socket.UnnamingRecipe;
import shadows.apotheosis.adventure.affix.socket.gem.GemItem;
import shadows.apotheosis.adventure.affix.socket.gem.GemManager;
import shadows.apotheosis.adventure.affix.socket.gem.bonus.GemBonus;
import shadows.apotheosis.adventure.affix.socket.gem.cutting.GemCuttingBlock;
import shadows.apotheosis.adventure.affix.socket.gem.cutting.GemCuttingMenu;
import shadows.apotheosis.adventure.boss.*;
import shadows.apotheosis.adventure.client.AdventureModuleClient;
import shadows.apotheosis.adventure.compat.AdventureTOPPlugin;
import shadows.apotheosis.adventure.compat.AdventureTwilightCompat;
import shadows.apotheosis.adventure.compat.GatewaysCompat;
import shadows.apotheosis.adventure.gen.BossDungeonFeature;
import shadows.apotheosis.adventure.gen.BossDungeonFeature2;
import shadows.apotheosis.adventure.gen.ItemFrameGemsProcessor;
import shadows.apotheosis.adventure.gen.RogueSpawnerFeature;
import shadows.apotheosis.adventure.loot.*;
import shadows.apotheosis.adventure.spawner.RandomSpawnerManager;
import shadows.apotheosis.ench.objects.GlowyBlockItem;
import shadows.apotheosis.util.NameHelper;
import shadows.placebo.block_entity.TickingBlockEntityType;
import shadows.placebo.config.Configuration;
import shadows.placebo.container.ContainerUtil;
import shadows.placebo.loot.LootSystem;
import shadows.placebo.util.RegistryEvent;

import java.io.File;
import java.util.*;

public class AdventureModuleTwo {
    @SubscribeEvent
    public void items(RegistryEvent.Register<Item> e) {

        e.getRegistry().register(new BlockItem((Block) koyukivirus.apothrarities.Apoth.Blocks.ANCIENT_REFORGING_TABLE.get(), (new Item.Properties()).tab(Apotheosis.APOTH_GROUP)), "ancient_simple_reforging_table");
        e.getRegistry().register(new BlockItem((Block) koyukivirus.apothrarities.Apoth.Blocks.ESOTERIC_REFORGING_TABLE.get(), (new Item.Properties()).tab(Apotheosis.APOTH_GROUP)), "esoteric_reforging_table");
    }

    @SubscribeEvent
    public void blocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(new ReforgingTableBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(4.0F, 1000.0F), LootRarity.ANCIENT), "ancient_reforging_table");
        e.getRegistry().register(new ReforgingTableBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).requiresCorrectToolForDrops().strength(4.0F, 1000.0F), LootRarity.ANCIENT), "esoteric_reforging_table");
    }
    @SubscribeEvent
    public void tiles(RegistryEvent.Register<BlockEntityType<?>> e) {
        e.getRegistry().register(new TickingBlockEntityType(ReforgingTableTile::new, ImmutableSet.of((Block) koyukivirus.apothrarities.Apoth.Blocks.ANCIENT_REFORGING_TABLE.get(), (Block) koyukivirus.apothrarities.Apoth.Blocks.ESOTERIC_REFORGING_TABLE.get()), true, false), "new_reforging_table");
    }
}
