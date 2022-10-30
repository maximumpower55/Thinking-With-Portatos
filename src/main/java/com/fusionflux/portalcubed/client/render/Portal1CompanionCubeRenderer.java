package com.fusionflux.portalcubed.client.render;

import com.fusionflux.portalcubed.PortalCubed;
import com.fusionflux.portalcubed.client.render.model.entity.Portal1CompanionCubeModel;
import com.fusionflux.portalcubed.entity.Portal1CompanionCubeEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class Portal1CompanionCubeRenderer extends MobEntityRenderer<Portal1CompanionCubeEntity, Portal1CompanionCubeModel> {
	private static final Identifier BASE_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/portal_1_companion_cube.png");
	protected final Portal1CompanionCubeModel model = new Portal1CompanionCubeModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(Portal1CompanionCubeModel.COMPANION_CUBE_MAIN_LAYER));

	public Portal1CompanionCubeRenderer(EntityRendererFactory.Context context) {
		super(context, new Portal1CompanionCubeModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(Portal1CompanionCubeModel.COMPANION_CUBE_MAIN_LAYER)), 0.5f);
	}




	@Override
	public Identifier getTexture(Portal1CompanionCubeEntity entity) {
		return BASE_TEXTURE;
	}
}
