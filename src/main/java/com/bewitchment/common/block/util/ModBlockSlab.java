package com.bewitchment.common.block.util;

import com.bewitchment.Bewitchment;
import com.bewitchment.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SuppressWarnings({"deprecation", "ConstantConditions", "NullableProblems"})
public class ModBlockSlab extends BlockSlab {
	private final boolean isDouble;
	public Block double_slab;
	private Block half;
	
	public ModBlockSlab(String name, Block base, String... oreDictionaryNames) {
		this(name, base, false);
		ModBlockSlab double_slab = new ModBlockSlab(getRegistryName().getPath() + "_double", base, true);
		double_slab.setCreativeTab(null);
		this.half = this;
		double_slab.half = this;
		Item item_slab = Util.registerItem(new ItemSlab(this, this, double_slab), name, oreDictionaryNames);
		ForgeRegistries.ITEMS.register(item_slab);
		Bewitchment.proxy.registerTexture(item_slab, "normal");
		this.double_slab = double_slab;
		ForgeRegistries.BLOCKS.register(double_slab);
		this.useNeighborBrightness = true;
	}
	
	private ModBlockSlab(String name, Block base, boolean isDouble) {
		super(base.getDefaultState().getMaterial());
		Util.registerBlock(this, name, base);
		this.setDefaultState(isDouble ? blockState.getBaseState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT) : blockState.getBaseState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT).withProperty(HALF, EnumBlockHalf.BOTTOM));
		this.isDouble = isDouble;
		this.fullBlock = isDouble;
	}
	
	@Override
	public String getTranslationKey(int meta) {
		return super.getTranslationKey();
	}
	
	@Override
	public boolean isDouble() {
		return isDouble;
	}
	
	@Override
	public IProperty<?> getVariantProperty() {
		return BlockPurpurSlab.VARIANT;
	}
	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return BlockPurpurSlab.Variant.DEFAULT;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return isDouble() ? getDefaultState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT) : getDefaultState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT).withProperty(HALF, meta == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return !isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP ? 1 : 0;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(half);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return Util.isTransparent(getDefaultState()) ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(half);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, HALF, BlockPurpurSlab.VARIANT);
	}
}