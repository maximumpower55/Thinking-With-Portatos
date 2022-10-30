// Made with Blockbench 4.0.4
// Exported for Minecraft version 1.17
// Paste this class into your mod and generate all required imports

package com.fusionflux.portalcubed.client.render.model.entity;

import com.fusionflux.portalcubed.PortalCubed;
import com.fusionflux.portalcubed.entity.MugEntity;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MugModel extends EntityModel<MugEntity> {
	public static final EntityModelLayer MUG_LAYER = new EntityModelLayer(new Identifier(PortalCubed.MODID,"mug"), "main");
	private final ModelPart bb_main;

	public MugModel(ModelPart root) {
		//  TODO: add bone fields here!
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.0F, -1.25F, 3.0F, 4.0F, 3.0F, new Dilation(0.01F))
				.uv(2, 2).mirrored().cuboid(1.5F, 2.0F, 0.25F, -3.0F, -4.0F, -3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 22.0F, -0.25F, 0.0F, 3.1416F, 0.0F));

		bone.addChild("cube_r1", ModelPartBuilder.create().uv(0, 7).cuboid(0.5F, -1.5F, 0.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, 0.0F, 1.25F, 0.0F, 1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(MugEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}
