package com.fusionflux.portalcubed.accessor;

import com.google.common.collect.Iterables;
import net.minecraft.entity.Entity;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.CollisionView;
import net.minecraft.world.border.WorldBorder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface CustomCollisionView extends CollisionView {

	default Iterable<VoxelShape> getPortalBlockCollisions(@Nullable Entity entity, Box box, VoxelShape portalBox) {
		return () -> new CustomBlockCollisionSpliteraror(this, entity, box,portalBox);
	}

	default Iterable<VoxelShape> getPortalCollisions(@Nullable Entity entity, Box box,VoxelShape portalBox) {
		List<VoxelShape> list = this.getEntityCollisions(entity, box);
		Iterable<VoxelShape> iterable = this.getPortalBlockCollisions(entity, box,portalBox);
		return list.isEmpty() ? iterable : Iterables.concat(list, iterable);
	}

	default boolean canPortalCollide(@Nullable Entity entity, Box box, VoxelShape portalBox) {
		CustomBlockCollisionSpliteraror customBlockCollisionSpliterator = new CustomBlockCollisionSpliteraror(this, entity, box,portalBox, true);

		while(customBlockCollisionSpliterator.hasNext()) {
			if (!customBlockCollisionSpliterator.next().isEmpty()) {
				return true;
			}
		}

		return false;
	}


	@Nullable
	private VoxelShape getWorldBorderCollisions(Entity entity, Box box) {
		WorldBorder worldBorder = this.getWorldBorder();
		// HEY QM PEOPLE! MAP THIS PLEASE
		return worldBorder.method_39459(entity, box) ? worldBorder.asVoxelShape() : null;
	}

	default boolean isPortalSpaceEmpty(Box box,VoxelShape portalBox) {
		return this.isPortalSpaceEmpty((Entity)null, box,portalBox);
	}

	default boolean isPortalSpaceEmpty(Entity entity,VoxelShape portalBox) {
		return this.isPortalSpaceEmpty(entity, entity.getBoundingBox(),portalBox);
	}


	default boolean isPortalSpaceEmpty(@Nullable Entity entity, Box box,VoxelShape portalBox) {
		for(VoxelShape voxelShape : this.getPortalBlockCollisions(entity, box,portalBox)) {
			if (!voxelShape.isEmpty()) {
				return false;
			}
		}

		if (!this.getEntityCollisions(entity, box).isEmpty()) {
			return false;
		} else if (entity == null) {
			return true;
		} else {
			VoxelShape voxelShape2 = this.getWorldBorderCollisions(entity, box);
			return voxelShape2 == null || !VoxelShapes.matchesAnywhere(voxelShape2, VoxelShapes.cuboid(box), BooleanBiFunction.AND);
		}
	}

}
