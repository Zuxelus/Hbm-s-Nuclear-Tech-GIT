package com.hbm.explosion;

import com.hbm.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExplosionBalefire {

	public int posX;
	public int posY;
	public int posZ;
	public int lastposX = 0;
	public int lastposZ = 0;
	public int radius;
	public int radius2;
	public World worldObj;
	private int n = 1;
	private int nlimit;
	private int shell;
	private int leg;
	private int element;
	
	public void saveToNbt(NBTTagCompound nbt, String name) {
		nbt.setInteger(name + "posX", posX);
		nbt.setInteger(name + "posY", posY);
		nbt.setInteger(name + "posZ", posZ);
		nbt.setInteger(name + "lastposX", lastposX);
		nbt.setInteger(name + "lastposZ", lastposZ);
		nbt.setInteger(name + "radius", radius);
		nbt.setInteger(name + "radius2", radius2);
		nbt.setInteger(name + "n", n);
		nbt.setInteger(name + "nlimit", nlimit);
		nbt.setInteger(name + "shell", shell);
		nbt.setInteger(name + "leg", leg);
		nbt.setInteger(name + "element", element);
	}
	
	public void readFromNbt(NBTTagCompound nbt, String name) {
		posX = nbt.getInteger(name + "posX");
		posY = nbt.getInteger(name + "posY");
		posZ = nbt.getInteger(name + "posZ");
		lastposX = nbt.getInteger(name + "lastposX");
		lastposZ = nbt.getInteger(name + "lastposZ");
		radius = nbt.getInteger(name + "radius");
		radius2 = nbt.getInteger(name + "radius2");
		n = nbt.getInteger(name + "n");
		nlimit = nbt.getInteger(name + "nlimit");
		shell = nbt.getInteger(name + "shell");
		leg = nbt.getInteger(name + "leg");
		element = nbt.getInteger(name + "element");
	}
	
	public ExplosionBalefire(int x, int y, int z, World world, int rad)
	{
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		
		this.worldObj = world;
		
		this.radius = rad;
		this.radius2 = this.radius * this.radius;

		this.nlimit = this.radius2 * 4;
	}
	
	public boolean update()
	{
		breakColumn(this.lastposX, this.lastposZ);
		this.shell = (int) Math.floor((Math.sqrt(n) + 1) / 2);
		if(shell == 0)
			shell = 1;
		int shell2 = this.shell * 2;
		this.leg = (int) Math.floor((this.n - (shell2 - 1) * (shell2 - 1)) / shell2);
		this.element = (this.n - (shell2 - 1) * (shell2 - 1)) - shell2 * this.leg - this.shell + 1;
		this.lastposX = this.leg == 0 ? this.shell : this.leg == 1 ? -this.element : this.leg == 2 ? -this.shell : this.element;
		this.lastposZ = this.leg == 0 ? this.element : this.leg == 1 ? this.shell : this.leg == 2 ? -this.element : -this.shell;
		this.n++;
		return this.n > this.nlimit;
	}

	@SuppressWarnings("deprecation")
	private void breakColumn(int x, int z)
	{
		int dist = (int) (radius - Math.sqrt(x * x + z * z));
		
		if (dist > 0) {
			int pX = posX + x;
			int pZ = posZ + z;
			
			int y  = worldObj.getHeight(pX, pZ);
			int maxdepth = (int) (10 + radius * 0.25);
			int depth = (int) ((maxdepth * dist / radius) + (Math.sin(dist * 0.15 + 2) * 2));//
			
			depth = Math.max(y - depth, 0);
			
			while(y > depth) {

				Block b = worldObj.getBlockState(new BlockPos(pX, y, pZ)).getBlock();
				
				if(b == ModBlocks.block_schrabidium_cluster) {
					
					if(worldObj.rand.nextInt(10) == 0) {
						worldObj.setBlockState(new BlockPos(pX, y + 1, pZ), ModBlocks.balefire.getDefaultState());
						worldObj.setBlockState(new BlockPos(pX, y, pZ), ModBlocks.block_euphemium_cluster.getStateFromMeta(b.getMetaFromState(worldObj.getBlockState(new BlockPos(pX, y, pZ)))), 3);
					}
					return;
				}
				
				worldObj.setBlockToAir(new BlockPos(pX, y, pZ));
				
				y--;
			}
			
			if(worldObj.rand.nextInt(10) == 0) {
				worldObj.setBlockState(new BlockPos(pX, depth + 1, pZ), ModBlocks.balefire.getDefaultState());
				
				Block b = worldObj.getBlockState(new BlockPos(pX, y, pZ)).getBlock();
				
				if(b == ModBlocks.block_schrabidium_cluster)
					worldObj.setBlockState(new BlockPos(pX, y, pZ), ModBlocks.block_euphemium_cluster.getStateFromMeta(b.getMetaFromState(worldObj.getBlockState(new BlockPos(pX, y, pZ)))), 3);
			}

			for(int i = depth; i > depth - 5; i--) {
				if(worldObj.getBlockState(new BlockPos(pX, i, pZ)).getBlock() == Blocks.STONE)
					worldObj.setBlockState(new BlockPos(pX, i, pZ), ModBlocks.sellafield_slaked.getDefaultState());
			}
		}
	}
	
	/*private void breakColumn(int x, int z)
	{
		MutableBlockPos pos = new BlockPos.MutableBlockPos();
		int dist = this.radius2 - (x * x + z * z);
		if (dist > 0)
		{
			int pX = posX + x;
			int pZ = posZ + z;
			
			int y = worldObj.getHeight(pX, pZ);
			float strength = (float)dist / (float) this.radius;
			while(y > 0) {
				
				if(strength <= 10) {
					if(worldObj.rand.nextInt(10) == 0){
						worldObj.setBlockState(pos.setPos(pX, y + 1, pZ), ModBlocks.balefire.getDefaultState());
						if(worldObj.getBlockState(pos.setPos(pX, y, pZ)).getBlock() == ModBlocks.block_schrabidium_cluster)
							worldObj.setBlockState(pos.setPos(pX, y, pZ), ModBlocks.block_euphemium_cluster.getDefaultState(), 3);
					}

					if(worldObj.getBlockState(pos.setPos(pX, y, pZ)).getBlock() == Blocks.STONE)
						worldObj.setBlockState(pos.setPos(pX, y, pZ), ModBlocks.sellafield_slaked.getDefaultState());
					if(worldObj.getBlockState(pos.setPos(pX, y - 1, pZ)).getBlock() == Blocks.STONE)
						worldObj.setBlockState(pos.setPos(pX, y - 1, pZ), ModBlocks.sellafield_slaked.getDefaultState());
					if(worldObj.getBlockState(pos.setPos(pX, y - 2, pZ)).getBlock() == Blocks.STONE)
						worldObj.setBlockState(pos.setPos(pX, y - 2, pZ), ModBlocks.sellafield_slaked.getDefaultState());
					if(worldObj.getBlockState(pos.setPos(pX, y - 3, pZ)).getBlock() == Blocks.STONE)
						worldObj.setBlockState(pos.setPos(pX, y - 3, pZ), ModBlocks.sellafield_slaked.getDefaultState());
					if(worldObj.getBlockState(pos.setPos(pX, y - 4, pZ)).getBlock() == Blocks.STONE)
						worldObj.setBlockState(pos.setPos(pX, y - 4, pZ), ModBlocks.sellafield_slaked.getDefaultState());
						
					return;
				}
				
				float hardness = worldObj.getBlockState(pos.setPos(pX, y, pZ)).getBlockHardness(worldObj, pos.setPos(pX, y, pZ));
				
				if(worldObj.getBlockState(pos.setPos(pX, y, pZ)).getMaterial().isLiquid())
					hardness = Blocks.AIR.getDefaultState().getBlockHardness(worldObj, pos.setPos(pX, y + 1, pZ));
				
				strength -= hardness;
				
				worldObj.setBlockToAir(pos.setPos(pX, y, pZ));
				
				y--;
			}
		}
	}*/
}
