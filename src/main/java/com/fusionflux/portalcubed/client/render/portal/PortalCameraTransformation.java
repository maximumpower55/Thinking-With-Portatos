package com.fusionflux.portalcubed.client.render.portal;

import java.util.ArrayDeque;

import com.fusionflux.portalcubed.entity.Portal;
import com.fusionflux.portalcubed.mixin.client.CameraAccessor;
import com.fusionflux.portalcubed.util.CameraControl;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public record PortalCameraTransformation(Portal portal, Vec3 beforeUsagePos, float beforeUsageYaw, float beforeUsagePitch) {
	public static final ArrayDeque<PortalCameraTransformation> stack = new ArrayDeque<>();

	public void transform(CameraControl ctrl, float tickDelta) {
		// TODO
	}

	public static void push(Portal portal) {
		final var camera = Minecraft.getInstance().gameRenderer.getMainCamera();
		stack.addLast(new PortalCameraTransformation(portal, camera.getPosition(), camera.getYRot(), camera.getXRot()));
	}

	public static void pop() {
		final var mainCamera = Minecraft.getInstance().gameRenderer.getMainCamera();
		final var transformation = stack.removeLast();

		((CameraAccessor) mainCamera).portalcubed$setPosition(transformation.beforeUsagePos);
		((CameraAccessor) mainCamera).portalcubed$setRotation(transformation.beforeUsageYaw, transformation.beforeUsagePitch);
	}
}
