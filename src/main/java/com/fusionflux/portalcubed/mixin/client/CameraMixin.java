package com.fusionflux.portalcubed.mixin.client;

import com.fusionflux.portalcubed.accessor.CameraExt;
import com.fusionflux.portalcubed.client.PortalCubedClient;
import com.fusionflux.portalcubed.client.render.portal.PortalCameraTransformation;
import com.fusionflux.portalcubed.util.CameraControl;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin implements CameraExt {
	@Shadow
	private BlockGetter level;
	@Shadow
	@Final
	private BlockPos.MutableBlockPos blockPosition;

	@Shadow private Entity entity;

	@Shadow private boolean initialized;

	@Shadow protected abstract void move(double distanceOffset, double verticalOffset, double horizontalOffset);

	@Shadow protected abstract double getMaxZoom(double startingDistance);

	@Shadow protected abstract void setPosition(Vec3 pos);
	@Shadow protected abstract void setRotation(float yaw, float pitch);

	@Inject(method = "setup", at = @At("RETURN"))
	private void portalCubed$redirectSetup(BlockGetter level, Entity entity, boolean detached, boolean thirdPersonReverse, float partialTick, CallbackInfo ci) {
		var cameraType = CameraType.FIRST_PERSON;
		if (detached) {
			cameraType = thirdPersonReverse ? CameraType.THIRD_PERSON_FRONT : CameraType.THIRD_PERSON_BACK;
		}
		final Camera camera = (Camera)(Object)this;
		final CameraControl ctrl = new CameraControl(camera.getPosition(), camera.getYRot(), camera.getXRot());
		PortalCubedClient.moveCameraIfDead(camera, entity, cameraType, partialTick, ctrl);
		PortalCubedClient.transformCameraIntersectingPortal(camera, entity, cameraType, partialTick, ctrl);

		final var transformation = PortalCameraTransformation.stack.peekLast();
		if (transformation != null) transformation.transform(ctrl, partialTick);

		if (ctrl.getPos() != camera.getPosition()) {
			setPosition(ctrl.getPos());
		}
		if (ctrl.getYaw() != camera.getYRot() || ctrl.getPitch() != camera.getXRot()) {
			setRotation(ctrl.getYaw(), ctrl.getPitch());
		}
	}

	@Override
	public FluidState portalcubed$getSubmergedFluidState() {
		return this.level.getFluidState(blockPosition);
	}

	@Override
	public void updateSimple(BlockGetter area, Entity focusedEntity) {
		this.level = area;
		this.entity = focusedEntity;
		initialized = true;
	}

	@Override
	public void backCameraUp(Vec3 from) {
		setPosition(from);
		move(-getMaxZoom(4), 0, 0);
	}

}
