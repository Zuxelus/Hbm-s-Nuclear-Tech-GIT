package com.hbm.entity.missile;

import java.util.ArrayList;
import java.util.List;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.entity.logic.EntityNukeExplosionMK4;
import com.hbm.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMissileMirv extends EntityMissileBaseAdvanced {

	public EntityMissileMirv(World p_i1582_1_) {
		super(p_i1582_1_);
	}

	public EntityMissileMirv(World world, float x, float y, float z, int a, int b) {
		super(world, x, y, z, a, b);
	}

	@Override
	public void onImpact() {
		
    	world.spawnEntity(EntityNukeExplosionMK4.statFac(world, BombConfig.missileRadius * 2, posX, posY, posZ));

		EntityNukeCloudSmall entity2 = new EntityNukeCloudSmall(this.world, 1000, BombConfig.missileRadius * 2 * 0.005F);
    	entity2.posX = this.posX;
    	entity2.posY = this.posY - 9;
    	entity2.posZ = this.posZ;
    	this.world.spawnEntity(entity2);
	}

	@Override
	public List<ItemStack> getDebris() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(ModItems.plate_titanium, 16));
		list.add(new ItemStack(ModItems.plate_steel, 20));
		list.add(new ItemStack(ModItems.plate_aluminium, 12));
		list.add(new ItemStack(ModItems.thruster_large, 1));
		list.add(new ItemStack(ModItems.circuit_targeting_tier5, 1));
		
		return list;
	}

	@Override
	public ItemStack getDebrisRareDrop() {
		return new ItemStack(ModItems.warhead_mirv);
	}

	@Override
	public RadarTargetType getTargetType() {
		return RadarTargetType.MISSILE_TIER4;
	}
}
