// Made with Blockbench 4.0.4
// Exported for Minecraft version 1.17
// Paste this class into your mod and generate all required imports

package com.fusionflux.portalcubed.client.render.model.entity;

import com.fusionflux.portalcubed.PortalCubed;
import com.fusionflux.portalcubed.entity.Portal1CompanionCubeEntity;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class Portal1CompanionCubeModel extends EntityModel<Portal1CompanionCubeEntity> {
	public static final EntityModelLayer COMPANION_CUBE_MAIN_LAYER = new EntityModelLayer(new Identifier(PortalCubed.MODID,"portal_1_companion_cube"), "main");
	private final ModelPart bb_main;

	public Portal1CompanionCubeModel(ModelPart root) {
		//  TODO: add bone fields here!
		this.bb_main = root.getChild("bone");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-5.5F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F))
				.uv(0, 20).cuboid(-5.5F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.5F)), ModelTransform.pivot(0.5F, 24.0F, 0.0F));

		bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 2).mirrored().cuboid(5.25F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, -5.2929F, 0.0F, 2.3562F, 0.0F, -3.1416F));

		bone.addChild("cube_r2", ModelPartBuilder.create().uv(0, 2).cuboid(5.25F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -5.2929F, 0.0F, -0.7854F, 0.0F, 0.0F));

		bone.addChild("cube_r3", ModelPartBuilder.create().uv(0, 2).mirrored().cuboid(5.25F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, -4.9929F, 0.0F, -2.3562F, 0.0F, -1.5708F));

		bone.addChild("cube_r4", ModelPartBuilder.create().uv(0, 2).cuboid(5.25F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -4.9929F, 0.0F, -2.3562F, 0.0F, 1.5708F));

		bone.addChild("cube_r5", ModelPartBuilder.create().uv(0, 2).cuboid(5.25F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -5.2929F, 0.0F, 0.0F, -1.5708F, -0.7854F));

		bone.addChild("cube_r6", ModelPartBuilder.create().uv(0, 2).mirrored().cuboid(5.25F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, -5.2929F, 0.0F, 0.0F, 1.5708F, 0.7854F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(Portal1CompanionCubeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
