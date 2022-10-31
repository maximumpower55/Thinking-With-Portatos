package com.fusionflux.portalcubed.util;

import com.fusionflux.portalcubed.entity.ExperimentalPortal;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;
import java.util.UUID;

public class PortalDataComponent implements CustomPortalDataComponent, AutoSyncedComponent {
	Vec3d axisW = null;
	Vec3d axisH = null;
	Vec3d axisOH=Vec3d.ZERO;
	Vec3d destination = null;
	Vec3d facing=Vec3d.ZERO;
	UUID player = null;
	private final ExperimentalPortal entity;

	public PortalDataComponent(ExperimentalPortal entity) {
		this.entity = entity;
	}

	@Override
	public Vec3d getAxisW() {
		return axisW;
	}

	@Override
	public Vec3d getAxisH() {
		return axisH;
	}

	@Override
	public Vec3d getOtherAxisH() {
		return axisOH;
	}


	@Override
	public void setOtherAxisH(Vec3d h) {
		axisOH = h;
		PortalCubedComponents.PORTAL_DATA.sync(entity);
	}

	@Override
	public Vec3d getDestination() {
		return destination;
	}

	@Override
	public void setDestination(Vec3d Destination) {
		destination = Destination;
		PortalCubedComponents.PORTAL_DATA.sync(entity);
	}

	@Override
	public Vec3d getOtherFacing() {
		return facing;
	}

	@Override
	public void setOtherFacing(Vec3d Facing) {
		facing = Facing;
		PortalCubedComponents.PORTAL_DATA.sync(entity);
	}

	@Override
	public UUID getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(UUID savedPlayer) {
		player = savedPlayer;
		PortalCubedComponents.PORTAL_DATA.sync(entity);
	}

	@Override
	public void teleportEntity(Vec3d teleportTo, Entity teleportedEntity, ExperimentalPortal otherPortal) {
		Vec3d teleportLocation = otherPortal.getPos();

		switch (otherPortal.getFacingDirection()) {
			case NORTH:
				teleportLocation.subtract(0,0,3);

			case SOUTH:
				teleportLocation.add(0,0,3);

			case WEST:
				teleportLocation.subtract(3,0,0);

			case EAST:
				teleportLocation.add(3,0,0);

			default:
				break;
		}

		teleportedEntity.teleport(teleportLocation.getX(), teleportLocation.getY() - 1, teleportLocation.getZ());

		PortalCubedComponents.PORTAL_DATA.sync(entity);
	}


	@Override
	public void setOrientation(Vec3d AxisW,Vec3d AxisH) {
		axisW = AxisW;
		axisH = AxisH;
		entity.syncRotations();
		PortalCubedComponents.PORTAL_DATA.sync(entity);
	}




	@Override
	public void readFromNbt(NbtCompound tag) {
		this.setOrientation(IPHelperDuplicate.getVec3d(tag, "axisW").normalize(),IPHelperDuplicate.getVec3d(tag, "axisH").normalize());
		this.setOtherAxisH(IPHelperDuplicate.getVec3d(tag, "axisOH").normalize());
		this.setDestination(IPHelperDuplicate.getVec3d(tag, "destination"));
		this.setOtherFacing(IPHelperDuplicate.getVec3d(tag, "facing"));
		String playerString = tag.getString("playerUUID");
		if(!Objects.equals(playerString, "null")) {
			player = UUID.fromString(tag.getString("playerUUID"));
		}else{
			player = null;
		}
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		IPHelperDuplicate.putVec3d(tag, "axisW", this.getAxisW());
		IPHelperDuplicate.putVec3d(tag, "axisH", this.getAxisH());
		IPHelperDuplicate.putVec3d(tag, "axisOH", this.getOtherAxisH());
		IPHelperDuplicate.putVec3d(tag, "destination", this.getDestination());
		IPHelperDuplicate.putVec3d(tag, "facing", this.getOtherFacing());
		if(player != null) {
			tag.putString("playerUUID", player.toString());
		}else{
			tag.putString("playerUUID", "null");
		}
	}
}
