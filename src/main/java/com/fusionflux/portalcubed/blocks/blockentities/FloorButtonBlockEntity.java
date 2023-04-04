package com.fusionflux.portalcubed.blocks.blockentities;

import com.fusionflux.portalcubed.blocks.HardLightBridgeEmitterBlock;
import com.fusionflux.portalcubed.blocks.PortalCubedBlocks;
import com.fusionflux.portalcubed.entity.*;
import com.fusionflux.portalcubed.sound.PortalCubedSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author sailKite
 * @author FusionFlux
 * <p>
 * Handles the operating logic for the {@link HardLightBridgeEmitterBlock} and their associated bridges.
 */
public class FloorButtonBlockEntity extends BlockEntity {


    public FloorButtonBlockEntity(BlockPos pos, BlockState state) {
        super(PortalCubedBlocks.FLOOR_BUTTON_BLOCK_ENTITY, pos, state);

    }

    public static void tick1(World world, BlockPos pos, BlockState state, FloorButtonBlockEntity blockEntity) {
        if (!world.isClient) {
            Direction storedDirec = blockEntity.getCachedState().get(Properties.FACING);
            Direction storedDirecOpp = storedDirec.getOpposite();
            BlockPos transPos = pos.offset(storedDirec);

            Box portalCheckBox = new Box(transPos);

            portalCheckBox = portalCheckBox.contract(Math.abs(storedDirecOpp.getOffsetX()), Math.abs(storedDirecOpp.getOffsetY()), Math.abs(storedDirecOpp.getOffsetZ())).offset(storedDirecOpp.getOffsetX() * .8125, storedDirecOpp.getOffsetY() * .8125, storedDirecOpp.getOffsetZ() * .8125);
            List<LivingEntity> entities = world.getNonSpectatingEntities(LivingEntity.class, portalCheckBox);

            boolean isPowered = false;
            for (LivingEntity living : entities) {
                if (living instanceof PlayerEntity || living instanceof StorageCubeEntity || living instanceof Portal1CompanionCubeEntity || living instanceof Portal1StorageCubeEntity || living instanceof OldApCubeEntity) {
                    if (living instanceof CorePhysicsEntity physicsEntity) {
                        if (physicsEntity.fizzling()) {
                            physicsEntity.addVelocity(0, 0.015, 0);
                        } else {
                            isPowered = true;
                            if (living instanceof StorageCubeEntity cube) {
                                cube.setButtonTimer(1);
                            }
                        }
                    } else {
                        isPowered = true;
                    }
                }
            }

            if (state.get(Properties.ENABLED) != isPowered) {
                blockEntity.updateState(state, isPowered);
            }
        }


    }

    public void updateState(BlockState state, boolean toggle) {
        if (world != null) {
            world.setBlockState(pos, state.with(Properties.ENABLED, toggle), 3);
            if (!world.isClient && state.get(Properties.ENABLED) != toggle) {
                world.playSound(
                    null, pos,
                    toggle ? PortalCubedSounds.FLOOR_BUTTON_PRESS_EVENT : PortalCubedSounds.FLOOR_BUTTON_RELEASE_EVENT,
                    SoundCategory.BLOCKS, 0.8f, 1f
                );
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
    }

}
