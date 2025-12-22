package com.vaneck.mclogicfurnace;

import com.vaneck.mclogicfurnace.block.NFurnaceBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.vaneck.mclogicfurnace.MCLogicFurnace.MODID;

public class ModBlockEntities {

    public static final BlockEntityType<NFurnaceBlockEntity> NFURNACE_BLOCK_ENTITY =
            register("netherrack_furnace", NFurnaceBlockEntity::new, ModBlocks.NFURNACE_BLOCK);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.of(MODID, name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void initialize() {
    }
}
