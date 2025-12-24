package com.vaneck.mclogicfurnace.block;

import com.vaneck.mclogicfurnace.ModBlockEntities;
import com.vaneck.mclogicfurnace.mixin.AbsFurnBEAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private boolean isActive;

    public NFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NFURNACE_BLOCK_ENTITY, pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.mclogicfurnace.furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new FurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
        markDirty();
    }

    public static void tick(World world, BlockPos pos, BlockState state, NFurnaceBlockEntity blockEntity) {
        AbsFurnBEAccessor accessor = (AbsFurnBEAccessor) blockEntity;

        if (blockEntity.isActive()) {
            accessor.setFuelTime(512);
        } else {
            accessor.setFuelTime(0);
        }
        accessor.setBurnTime(accessor.getFuelTime());

        if (!blockEntity.inventory.get(1).isEmpty()) {
            grantAdvancement(world, pos);
        }

        AbstractFurnaceBlockEntity.tick(world, pos, state, blockEntity);
    }

    private static void grantAdvancement(World world, BlockPos blockPos) {
        if (world.isClient()) return;
        String advancement_ = "mclogicfurnace/need_more_fuel";
        String criterion = "mclogicfurnace:fuel";
        world.getPlayers().stream()
                .filter(p -> p.getPos().isInRange(blockPos.toCenterPos(), 5))
                .forEach(player -> {
                    if (player instanceof ServerPlayerEntity serverPlayer)
                        serverPlayer.getAdvancementTracker().grantCriterion(
                                Objects.requireNonNull(Objects.requireNonNull(serverPlayer.getServer())
                                        .getAdvancementLoader()
                                        .get(Identifier.of("minecraft", advancement_))), criterion);
                });
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("IsActive", this.isActive);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        setActive(nbt.getBoolean("IsActive"));
        this.isActive = nbt.getBoolean("IsActive");
    }

    @Override
    public @Nullable BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

}
