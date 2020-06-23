package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class FortuneCourage extends Fortune {
	public FortuneCourage() {
		super(new ResourceLocation(Bewitchment.MODID, "courage"), false, (60), (60 * 10));
	}
	
	@Override
	public boolean apply(EntityPlayer player) {
		if (!player.world.isRemote && player.getAttackingEntity() != null) player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 215, 1 + player.world.getDifficulty().ordinal(), false, false));
		return true;
	}
}