package com.fusionflux.portalcubed.client.render;

import com.fusionflux.portalcubed.PortalCubed;
import com.fusionflux.portalcubed.client.render.model.entity.LilPineappleModel;
import com.fusionflux.portalcubed.entity.LilPineappleEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.Objects;

public class LilPineappleRenderer extends MobEntityRenderer<LilPineappleEntity, LilPineappleModel> {
	private static final Identifier BASE_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_pineapple.png");
	private static final Identifier PROUD_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_proud.png");
	private static final Identifier ACE_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_ace.png");
	private static final Identifier AGENDER_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_agender.png");
	private static final Identifier ARO_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_aro.png");
	private static final Identifier BI_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_bi.png");
	private static final Identifier GENDERFLUID_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_genderfluid.png");
	private static final Identifier LESBIAN_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_lesbian.png");
	private static final Identifier NONBINARY_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_nonbinary.png");
	private static final Identifier PAN_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_pan.png");
	private static final Identifier TRANS_TEXTURE = new Identifier(PortalCubed.MODID, "textures/entity/lil_prideapple_trans.png");

	protected final LilPineappleModel model = new LilPineappleModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(LilPineappleModel.LIL_PINEAPPLE));

	public LilPineappleRenderer(EntityRendererFactory.Context context) {
		super(context, new LilPineappleModel(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(LilPineappleModel.LIL_PINEAPPLE)), 0.5f);
	}




	@Override
	public Identifier getTexture(LilPineappleEntity entity) {
		if(entity.getCustomName() != null) {
			if (Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "proud")) {
				return PROUD_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "ace")){
				return ACE_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "aro")){
				return ARO_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "agender")){
				return AGENDER_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "bi")){
				return BI_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "genderfluid")){
				return GENDERFLUID_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "lesbian")){
				return LESBIAN_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "nonbinary")){
				return NONBINARY_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "pan")){
				return PAN_TEXTURE;
			}
			if(Objects.equals(entity.getCustomName().getString().toLowerCase(Locale.ROOT), "trans")){
				return TRANS_TEXTURE;
			}
		}
		return BASE_TEXTURE;
	}
}
