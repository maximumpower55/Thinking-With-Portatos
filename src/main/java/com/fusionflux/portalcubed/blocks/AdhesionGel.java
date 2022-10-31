package com.fusionflux.portalcubed.blocks;

import com.fusionflux.gravity_api.api.GravityChangerAPI;
import com.fusionflux.gravity_api.util.Gravity;
import com.fusionflux.gravity_api.util.RotationUtil;
import com.fusionflux.portalcubed.client.AdhesionGravityVerifier;
import com.fusionflux.portalcubed.entity.EntityAttachments;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AdhesionGel extends GelFlat {

	public AdhesionGel(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(UP, false).with(DOWN, false));
	}

	public static final BiMap<Direction, BooleanProperty> dirToProperty = ImmutableBiMap.of(
			Direction.NORTH, Properties.NORTH,
			Direction.SOUTH, Properties.SOUTH,
			Direction.EAST, Properties.EAST,
			Direction.WEST, Properties.WEST,
			Direction.UP, Properties.UP,
			Direction.DOWN, Properties.DOWN
	);


	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		this.addCollisionEffects(world, entity, pos, state);
	}


	public Vec3d getGravityFromState(BlockState state) {
		Vec3d result = Vec3d.ZERO;
		final Vec3d[] finalResult = {result}; // bruh
		BiMap<BooleanProperty, Direction> propertyToDir = dirToProperty.inverse();
		state.getProperties().stream().map(property -> ((BooleanProperty) property)).filter(property -> state.get(property) && !property.getName().equals("waterlogged")).map(property -> Vec3d.of(propertyToDir.get(property).getVector())).forEach(vec -> finalResult[0] = finalResult[0].add(vec));
		result = finalResult[0];
		if (state.get(Properties.SOUTH) && state.get(Properties.NORTH)) {
			result = result.add(0, 0, 2);
		}
		if (state.get(Properties.EAST) && state.get(Properties.WEST)) {
			result = result.add(2, 0, 0);
		}

		return result;
	}

	/**
	 * what. the. f**k.
	 */
	private void addCollisionEffects(World world, Entity entity, BlockPos pos, BlockState state) {
		Vec3d vec3dLast = ((EntityAttachments) entity).portalcubed$getLastVel();

		PacketByteBuf info = AdhesionGravityVerifier.packInfo(pos);

		Vec3d direction = getGravityFromState(state);

		Vec3d preChange;

		direction = RotationUtil.vecWorldToPlayer(direction, GravityChangerAPI.getGravityDirection(entity));
		if (entity instanceof ClientPlayerEntity) {
			GravityChangerAPI.addGravityClient((ClientPlayerEntity)entity, AdhesionGravityVerifier.newFieldGravity(GravityChangerAPI.getGravityDirection(entity)), AdhesionGravityVerifier.FIELD_GRAVITY_SOURCE, info);
		} else {
			if(!(entity instanceof PlayerEntity))
				GravityChangerAPI.addGravity(entity, new Gravity(GravityChangerAPI.getGravityDirection(entity), 10, 2, "gravity_plate"));
		}

		if (((EntityAttachments) entity).portalcubed$getGelTimer() == 0) {
			if (entity.verticalCollision) {
				if (direction.y == 1 || Math.abs(direction.y) == 2 && vec3dLast.getY() > 0) {
					preChange = RotationUtil.vecPlayerToWorld(new Vec3d(0, 1, 0), GravityChangerAPI.getGravityDirection(entity));
					if (entity instanceof ClientPlayerEntity) {
						((EntityAttachments) entity).portalcubed$setGelTimer(10);
						GravityChangerAPI.addGravityClient((ClientPlayerEntity) entity, AdhesionGravityVerifier.newFieldGravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z)), AdhesionGravityVerifier.FIELD_GRAVITY_SOURCE, info);
					} else {
						if (!(entity instanceof PlayerEntity)) {
							((EntityAttachments) entity).portalcubed$setGelTimer(10);
							GravityChangerAPI.addGravity(entity, new Gravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z), 10, 2, "gravity_plate"));
						}
					}
				}
			}
			if (entity.horizontalCollision) {
				if (direction.z == -1 || Math.abs(direction.z) == 2 && vec3dLast.getZ() < 0) {
					preChange = RotationUtil.vecPlayerToWorld(new Vec3d(0, 0, -1), GravityChangerAPI.getGravityDirection(entity));
					if (entity instanceof ClientPlayerEntity) {
						((EntityAttachments) entity).portalcubed$setGelTimer(10);
						GravityChangerAPI.addGravityClient((ClientPlayerEntity) entity, AdhesionGravityVerifier.newFieldGravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z)), AdhesionGravityVerifier.FIELD_GRAVITY_SOURCE, info);
					} else {
						if (!(entity instanceof PlayerEntity)) {
							((EntityAttachments) entity).portalcubed$setGelTimer(10);
							GravityChangerAPI.addGravity(entity, new Gravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z), 10, 2, "gravity_plate"));
						}
					}
				}
				if (direction.z == 1 || Math.abs(direction.z) == 2 && vec3dLast.getZ() > 0) {
					preChange = RotationUtil.vecPlayerToWorld(new Vec3d(0, 0, 1), GravityChangerAPI.getGravityDirection(entity));
					if (entity instanceof ClientPlayerEntity) {
						((EntityAttachments) entity).portalcubed$setGelTimer(10);
						GravityChangerAPI.addGravityClient((ClientPlayerEntity) entity, AdhesionGravityVerifier.newFieldGravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z)), AdhesionGravityVerifier.FIELD_GRAVITY_SOURCE, info);
					} else {
						if (!(entity instanceof PlayerEntity)) {
							((EntityAttachments) entity).portalcubed$setGelTimer(10);
							GravityChangerAPI.addGravity(entity, new Gravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z), 10, 2, "gravity_plate"));
						}
					}
				}
				if (direction.x == 1 || Math.abs(direction.x) == 2 && vec3dLast.getX() > 0) {
					preChange = RotationUtil.vecPlayerToWorld(new Vec3d(1, 0, 0), GravityChangerAPI.getGravityDirection(entity));
					if (entity instanceof ClientPlayerEntity) {
						((EntityAttachments) entity).portalcubed$setGelTimer(10);
						GravityChangerAPI.addGravityClient((ClientPlayerEntity) entity, AdhesionGravityVerifier.newFieldGravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z)), AdhesionGravityVerifier.FIELD_GRAVITY_SOURCE, info);
					} else {
						if (!(entity instanceof PlayerEntity)) {
							((EntityAttachments) entity).portalcubed$setGelTimer(10);
							GravityChangerAPI.addGravity(entity, new Gravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z), 10, 2, "gravity_plate"));
						}
					}
				}
				if (direction.x == -1 || Math.abs(direction.x) == 2 && vec3dLast.getX() < 0) {
					preChange = RotationUtil.vecPlayerToWorld(new Vec3d(-1, 0, 0), GravityChangerAPI.getGravityDirection(entity));
					if (entity instanceof ClientPlayerEntity) {
						((EntityAttachments) entity).portalcubed$setGelTimer(10);
						GravityChangerAPI.addGravityClient((ClientPlayerEntity) entity, AdhesionGravityVerifier.newFieldGravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z)), AdhesionGravityVerifier.FIELD_GRAVITY_SOURCE, info);
					} else {
						if (!(entity instanceof PlayerEntity)) {
							((EntityAttachments) entity).portalcubed$setGelTimer(10);
							GravityChangerAPI.addGravity(entity, new Gravity(Direction.fromVector((int) preChange.x, (int) preChange.y, (int) preChange.z), 10, 2, "gravity_plate"));
						}
					}
				}
			}
		}
	}

	@Override
	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
		return true;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

}
