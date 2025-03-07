package com.hbm.blocks.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.IBomb;
import com.hbm.interfaces.IMultiBlock;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemLock;
import com.hbm.tileentity.machine.TileEntityVaultDoor;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class VaultDoor extends BlockContainer implements IBomb, IMultiBlock {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public VaultDoor(Material materialIn, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVaultDoor();
	}

	@Override
	public void explode(World world, BlockPos pos) {
		TileEntityVaultDoor te = (TileEntityVaultDoor) world.getTileEntity(pos);
		
		if(!te.isLocked())
			te.tryToggle();
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntityVaultDoor te = (TileEntityVaultDoor) world.getTileEntity(pos);
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
		int i = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if(i == 0)
		{
			//frame
			if(!(te.placeDummy(x + 1, y, z) &&
				te.placeDummy(x + 2, y, z) &&
				te.placeDummy(x + 2, y + 1, z) &&
				te.placeDummy(x + 2, y + 2, z) &&
				te.placeDummy(x + 2, y + 3, z) &&
				te.placeDummy(x + 2, y + 4, z) &&
				te.placeDummy(x + 1, y + 4, z) &&
				te.placeDummy(x, y + 4, z) &&
				te.placeDummy(x - 1, y + 4, z) &&
				te.placeDummy(x - 2, y + 4, z) &&
				te.placeDummy(x - 2, y + 3, z) &&
				te.placeDummy(x - 2, y + 2, z) &&
				te.placeDummy(x - 2, y + 1, z) &&
				te.placeDummy(x - 2, y, z) &&
				te.placeDummy(x - 1, y, z) &&
				//cog
				te.placeDummy(x - 1, y + 1, z) &&
				te.placeDummy(x - 1, y + 2, z) &&
				te.placeDummy(x - 1, y + 3, z) &&
				te.placeDummy(x, y + 1, z) &&
				te.placeDummy(x, y + 2, z) &&
				te.placeDummy(x, y + 3, z) &&
				te.placeDummy(x + 1, y + 1, z) &&
				te.placeDummy(x + 1, y + 2, z) &&
				te.placeDummy(x + 1, y + 3, z) &&
				//teeth
				te.placeDummy(x + 2, y, z + 1) &&
				te.placeDummy(x + 1, y, z + 1) &&
				te.placeDummy(x, y, z + 1) &&
				te.placeDummy(x - 1, y, z + 1) &&
				te.placeDummy(x - 2, y, z + 1))) {
				
				world.destroyBlock(pos, true);
			}
		}
		if(i == 1)
		{
			
			//frame
			if(!(te.placeDummy(x, y, z + 1) &&
				te.placeDummy(x, y, z + 2) &&
				te.placeDummy(x, y + 1, z + 2) &&
				te.placeDummy(x, y + 2, z + 2) &&
				te.placeDummy(x, y + 3, z + 2) &&
				te.placeDummy(x, y + 4, z + 2) &&
				te.placeDummy(x, y + 4, z + 1) &&
				te.placeDummy(x, y + 4, z) &&
				te.placeDummy(x, y + 4, z - 1) &&
				te.placeDummy(x, y + 4, z - 2) &&
				te.placeDummy(x, y + 3, z - 2) &&
				te.placeDummy(x, y + 2, z - 2) &&
				te.placeDummy(x, y + 1, z - 2) &&
				te.placeDummy(x, y, z - 2) &&
				te.placeDummy(x, y, z - 1) &&
				//cog
				te.placeDummy(x, y + 1, z - 1) &&
				te.placeDummy(x, y + 2, z - 1) &&
				te.placeDummy(x, y + 3, z - 1) &&
				te.placeDummy(x, y + 1, z) &&
				te.placeDummy(x, y + 2, z) &&
				te.placeDummy(x, y + 3, z) &&
				te.placeDummy(x, y + 1, z + 1) &&
				te.placeDummy(x, y + 2, z + 1) &&
				te.placeDummy(x, y + 3, z + 1) &&
				//teeth
				te.placeDummy(x - 1, y, z + 2) &&
				te.placeDummy(x - 1, y, z + 1) &&
				te.placeDummy(x - 1, y, z) &&
				te.placeDummy(x - 1, y, z - 1) &&
				te.placeDummy(x - 1, y, z - 2))) {
				
				world.destroyBlock(pos, true);
			}
		}
		if(i == 2)
		{
			
			//frame
			if(!(te.placeDummy(x + 1, y, z) &&
				te.placeDummy(x + 2, y, z) &&
				te.placeDummy(x + 2, y + 1, z) &&
				te.placeDummy(x + 2, y + 2, z) &&
				te.placeDummy(x + 2, y + 3, z) &&
				te.placeDummy(x + 2, y + 4, z) &&
				te.placeDummy(x + 1, y + 4, z) &&
				te.placeDummy(x, y + 4, z) &&
				te.placeDummy(x - 1, y + 4, z) &&
				te.placeDummy(x - 2, y + 4, z) &&
				te.placeDummy(x - 2, y + 3, z) &&
				te.placeDummy(x - 2, y + 2, z) &&
				te.placeDummy(x - 2, y + 1, z) &&
				te.placeDummy(x - 2, y, z) &&
				te.placeDummy(x - 1, y, z) &&
				//cog
				te.placeDummy(x - 1, y + 1, z) &&
				te.placeDummy(x - 1, y + 2, z) &&
				te.placeDummy(x - 1, y + 3, z) &&
				te.placeDummy(x, y + 1, z) &&
				te.placeDummy(x, y + 2, z) &&
				te.placeDummy(x, y + 3, z) &&
				te.placeDummy(x + 1, y + 1, z) &&
				te.placeDummy(x + 1, y + 2, z) &&
				te.placeDummy(x + 1, y + 3, z) &&
				//teeth
				te.placeDummy(x + 2, y, z - 1) &&
				te.placeDummy(x + 1, y, z - 1) &&
				te.placeDummy(x, y, z - 1) &&
				te.placeDummy(x - 1, y, z - 1) &&
				te.placeDummy(x - 2, y, z - 1))) {
				
				world.destroyBlock(pos, true);
			}
		}
		if(i == 3)
		{
			
			//frame
			if(!(te.placeDummy(x, y, z + 1) &&
				te.placeDummy(x, y, z + 2) &&
				te.placeDummy(x, y + 1, z + 2) &&
				te.placeDummy(x, y + 2, z + 2) &&
				te.placeDummy(x, y + 3, z + 2) &&
				te.placeDummy(x, y + 4, z + 2) &&
				te.placeDummy(x, y + 4, z + 1) &&
				te.placeDummy(x, y + 4, z) &&
				te.placeDummy(x, y + 4, z - 1) &&
				te.placeDummy(x, y + 4, z - 2) &&
				te.placeDummy(x, y + 3, z - 2) &&
				te.placeDummy(x, y + 2, z - 2) &&
				te.placeDummy(x, y + 1, z - 2) &&
				te.placeDummy(x, y, z - 2) &&
				te.placeDummy(x, y, z - 1) &&
				//cog
				te.placeDummy(x, y + 1, z - 1) &&
				te.placeDummy(x, y + 2, z - 1) &&
				te.placeDummy(x, y + 3, z - 1) &&
				te.placeDummy(x, y + 1, z) &&
				te.placeDummy(x, y + 2, z) &&
				te.placeDummy(x, y + 3, z) &&
				te.placeDummy(x, y + 1, z + 1) &&
				te.placeDummy(x, y + 2, z + 1) &&
				te.placeDummy(x, y + 3, z + 1) &&
				//teeth
				te.placeDummy(x + 1, y, z + 2) &&
				te.placeDummy(x + 1, y, z + 1) &&
				te.placeDummy(x + 1, y, z) &&
				te.placeDummy(x + 1, y, z - 1) &&
				te.placeDummy(x + 1, y, z - 2))) {
				
    			world.destroyBlock(pos, true);
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(world.isRemote)
		{
			return true;
		} else if(player.getHeldItem(hand).getItem() instanceof ItemLock || player.getHeldItem(hand).getItem() == ModItems.key_kit) {
			return false;
			
		} if(!player.isSneaking()) {
			
			TileEntityVaultDoor entity = (TileEntityVaultDoor) world.getTileEntity(pos);
			if(entity != null)
			{
				if(entity.isLocked()) {
					if(entity.canAccess(player))
						entity.tryToggle();
				} else {
					entity.tryToggle();
				}
			}
			
			return true;
		} else {
			
			TileEntityVaultDoor entity = (TileEntityVaultDoor) world.getTileEntity(pos);
			if(entity != null)
			{
				entity.type++;
				if(entity.type >= TileEntityVaultDoor.maxTypes)
					entity.type = 0;
			}
			return true;
		}
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
	}

}
