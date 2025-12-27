package com.vaneck.mclogicfurnace;

import com.vaneck.mclogicfurnace.block.NFurnaceBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void initialize() {
    }
}
