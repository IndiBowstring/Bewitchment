package com.bewitchment.client.render.entity.spirit.demon;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.model.entity.spirit.demon.ModelBafometyr;
import com.bewitchment.common.entity.spirit.demon.EntityBafometyr;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Created by Joseph on 4/23/2020.
 */
public class RenderBafometyr extends RenderLiving<EntityBafometyr> {
	private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MODID, "textures/entity/bafometyr.png");
	private static final ResourceLocation[] FLAMES = {new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_1.png"), new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_2"), new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_3"), new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_4"), new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_5"), new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_6"), new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_7"), new ResourceLocation(Bewitchment.MODID, "textures/entity/layer/bafometyr_fire_8")};
	
	public RenderBafometyr(RenderManager manager) {
		this(manager, new ModelBafometyr());
	}
	
	protected RenderBafometyr(RenderManager manager, ModelBase model) {
		super(manager, model, 0.3f);
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityBafometyr entity) {
		return TEX;
	}
}
