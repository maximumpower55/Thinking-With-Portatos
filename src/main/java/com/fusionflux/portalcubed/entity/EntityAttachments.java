package com.fusionflux.portalcubed.entity;

import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public interface EntityAttachments {

	boolean portalcubed$isBounced();

	void portalcubed$setBounced(boolean bounced);

	boolean portalcubed$isInFunnel();

	void portalcubed$setInFunnel(boolean inFunnel);

	int portalcubed$getFunnelTimer();

	void portalcubed$setFunnelTimer(int funnelTimer);

	Direction portalcubed$getDirection();

	void portalcubed$setDirection(Direction direction);

	double portalcubed$getMaxFallSpeed();

	void portalcubed$setMaxFallSpeed(double maxFallSpeed);

	double portalcubed$getMaxFallHeight();

	int portalcubed$getGelTimer();

	void portalcubed$setGelTimer(int funnelTimer);

	int portalcubed$getGelChangeTimer();

	void portalcubed$setGelChangeTimer(int funnelTimer);

	Vec3d portalcubed$getLastVel();

	Vec3d portalcubed$getLastPos();

	void portalcubed$setMaxFallHeight(double maxFallHeight);

	void portalcubed$setServerVel(Vec3d serverVel);

	Vec3d portalcubed$getServerVel();

	void portalcubed$setTeleportUUID(UUID serverVel);

	UUID portalcubed$getTeleportUUID();

	void portalcubed$setShouldTeleport(boolean shouldTeleport);

	boolean portalcubed$getShouldTeleport();

}
