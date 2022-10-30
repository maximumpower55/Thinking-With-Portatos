package com.fusionflux.portalcubed.accessor;

import com.fusionflux.portalcubed.entity.CorePhysicsEntity;
import com.fusionflux.portalcubed.entity.ExperimentalPortal;
import com.fusionflux.portalcubed.util.ButtonComponent;
import com.fusionflux.portalcubed.util.CustomPortalDataComponent;
import com.fusionflux.portalcubed.util.PortalCubedComponent;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CalledValues {

	public static final ComponentKey<PortalCubedComponent> ENTITY_COMPONENT =
			ComponentRegistry.getOrCreate(new Identifier("portalcubed", "entity_component"), PortalCubedComponent.class);

	// workaround for a CCA bug; maybeGet throws an NPE in internal code if the DataTracker isn't initialized
	// null check the component container to avoid it
	private static <C extends Component, V> Optional<C> maybeGetSafe(ComponentKey<C> key, @Nullable V provider) {
		if (provider instanceof ComponentProvider p) {
			var cc = p.getComponentContainer();
			if (cc != null) {
				return Optional.ofNullable(key.getInternal(cc));
			}
		}
		return Optional.empty();
	}

	public static boolean getSwapTimer(Entity entity) {
		return maybeGetSafe(ENTITY_COMPONENT, entity).map(PortalCubedComponent::getSwapGravity).orElse(false);
	}

	public static void setSwapTimer(Entity entity, boolean setValue) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.setSwapGravity(setValue));
	}

	public static VoxelShape getPortalCutout(Entity entity) {
		return maybeGetSafe(ENTITY_COMPONENT, entity).map(PortalCubedComponent::getPortalCutout).orElse(VoxelShapes.empty());
	}

	public static void setPortalCutout(Entity entity, VoxelShape setValue) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.setPortalCutout(setValue));
	}

	public static boolean getHasTeleportationHappened(Entity entity) {
		return maybeGetSafe(ENTITY_COMPONENT, entity).map(PortalCubedComponent::getHasTeleportationHappened).orElse(false);
	}

	public static void setHasTeleportationHappened(Entity entity, boolean setValue) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.setHasTeleportationHappened(setValue));
	}

	public static void teleportSync(Entity entity, Vec3d TeleportTo, Direction dira, Direction dirb) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.teleport(TeleportTo,dira,dirb));
	}

	public static UUID getCubeUUID(Entity entity) {
		return maybeGetSafe(ENTITY_COMPONENT, entity).map(PortalCubedComponent::getCubeUUID).orElse(null);
	}

	public static void setCubeUUID(Entity entity, UUID setValue) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.setCubeUUID(setValue));
	}

	public static List<UUID> getPortals(Entity entity) {
		return maybeGetSafe(ENTITY_COMPONENT, entity).map(PortalCubedComponent::getPortals).orElse(new ArrayList<>());
	}

	public static void setPortals(Entity entity, List<UUID> setValue) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.setPortals(setValue));
	}

	public static void addPortals(Entity entity, UUID setValue) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.addPortals(setValue));
	}
	public static void removePortals(Entity entity, UUID setValue) {
		maybeGetSafe(ENTITY_COMPONENT, entity).ifPresent(gc -> gc.removePortals(setValue));
	}

	public static final ComponentKey<CustomPortalDataComponent> PORTAL_DATA =
			ComponentRegistry.getOrCreate(new Identifier("portalcubed", "portal_data"), CustomPortalDataComponent.class);

	public static Vec3d getAxisW(Entity entity) {
		return maybeGetSafe(PORTAL_DATA, entity).map(CustomPortalDataComponent::getAxisW).orElse(null);
	}

	public static void setOrientation(ExperimentalPortal entity, Vec3d AxisW,Vec3d AxisH) {
		maybeGetSafe(PORTAL_DATA, entity).ifPresent(gc -> gc.setOrientation(AxisW,AxisH));
	}

	public static Vec3d getAxisH(Entity entity) {
		return maybeGetSafe(PORTAL_DATA, entity).map(CustomPortalDataComponent::getAxisH).orElse(null);
	}

	public static Vec3d getOtherAxisH(Entity entity) {
		return maybeGetSafe(PORTAL_DATA, entity).map(CustomPortalDataComponent::getOtherAxisH).orElse(null);
	}

	public static void setOtherAxisH(ExperimentalPortal entity, Vec3d AxisH) {
		maybeGetSafe(PORTAL_DATA, entity).ifPresent(gc -> gc.setOtherAxisH(AxisH));
	}

	public static void setDestination(ExperimentalPortal entity, Vec3d Destination) {
		maybeGetSafe(PORTAL_DATA, entity).ifPresent(gc -> gc.setDestination(Destination));
	}

	public static Vec3d getDestination(Entity entity) {
		return maybeGetSafe(PORTAL_DATA, entity).map(CustomPortalDataComponent::getDestination).orElse(null);
	}

	public static void setOtherFacing(ExperimentalPortal entity, Vec3d Destination) {
		maybeGetSafe(PORTAL_DATA, entity).ifPresent(gc -> gc.setOtherFacing(Destination));
	}

	public static Vec3d getOtherFacing(Entity entity) {
		return maybeGetSafe(PORTAL_DATA, entity).map(CustomPortalDataComponent::getOtherFacing).orElse(Vec3d.ZERO);
	}

	public static void setPlayer(ExperimentalPortal entity, UUID playerUUID) {
		maybeGetSafe(PORTAL_DATA, entity).ifPresent(gc -> gc.setPlayer(playerUUID));
	}

	public static UUID getPlayer(Entity entity) {
		return maybeGetSafe(PORTAL_DATA, entity).map(CustomPortalDataComponent::getPlayer).orElse(null);
	}

	public static void teleportEntity(ExperimentalPortal entity, Vec3d TeleportTo,Entity TeleportedEntity,ExperimentalPortal OtherPortal) {
		maybeGetSafe(PORTAL_DATA, entity).ifPresent(gc -> gc.teleportEntity(TeleportTo,TeleportedEntity,OtherPortal));
	}

	public static final ComponentKey<ButtonComponent> CUBE_DATA =
			ComponentRegistry.getOrCreate(new Identifier("portalcubed", "cube_component"), ButtonComponent.class);


	public static boolean getOnButton(CorePhysicsEntity entity) {
		return maybeGetSafe(CUBE_DATA, entity).map(ButtonComponent::getOnButton).orElse(false);
	}

	public static void setOnButton(CorePhysicsEntity entity, boolean setValue) {
		maybeGetSafe(CUBE_DATA, entity).ifPresent(gc -> gc.setOnButton(setValue));
	}

}
