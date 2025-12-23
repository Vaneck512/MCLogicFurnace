package com.vaneck.mclogicfurnace.block;

import com.vaneck.mclogicfurnace.ModBlockEntities;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NFurnaceBlock extends AbstractFurnaceBlock {
    public NFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof NFurnaceBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
            player.incrementStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NFurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.NFURNACE_BLOCK_ENTITY, AbstractFurnaceBlockEntity::tick);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        NFurnaceBlockEntity blockEntity = (NFurnaceBlockEntity)world.getBlockEntity(pos);
        if  (world.isClient) {
            return ActionResult.SUCCESS;
        }

        if (itemStack.isOf(Items.FIRE_CHARGE) || itemStack.isOf(Items.FLINT_AND_STEEL)) {
            assert blockEntity != null;
            if (blockEntity.isActive())
                super.onUse(state, world, pos, player, hand, hit);
        } else if (itemStack.isIn(ItemTags.SHOVELS)) {
            assert blockEntity != null;
            if (!blockEntity.isActive())
                super.onUse(state, world, pos, player, hand, hit);
        } else {
            super.onUse(state, world, pos, player, hand, hit);
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("tooltip.mclogicfurnace.tooltip1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("tooltip.mclogicfurnace.tooltip2").formatted(Formatting.GRAY));
    }

}

