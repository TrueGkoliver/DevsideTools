package com.gkoliver.devsidetools.common.item;

import java.util.Objects;

import javax.annotation.Nullable;

import com.farcr.swampexpansion.core.registry.SwampExEntities;
import com.gkoliver.devsidetools.core.registry.DevsideToolsItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemNBTSpawnEgg extends Item {
	public CompoundNBT nbt;
	private final int primaryColor;
    private final int secondaryColor;
    private final EntityType<?> typeIn;
	public ItemNBTSpawnEgg(EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder, CompoundNBT nbt) {
		super(builder);
		this.typeIn = typeIn;
		this.primaryColor = primaryColorIn;
		this.secondaryColor = secondaryColorIn;
		this.nbt = nbt;
		DevsideToolsItems.NBT_SPAWN_EGGS.add(this);
	}
	
	public boolean isSlabber = false;
	public ItemNBTSpawnEgg(int primaryColorIn, int secondaryColorIn, Properties builder, CompoundNBT nbt) {
		this(null, primaryColorIn, secondaryColorIn, builder, nbt);
		isSlabber = true;
	}
	
	public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
	      if (isSlabber) {
	    	  return SwampExEntities.SLABFISH.get();
	      }
	      else {
	    	  return this.typeIn;
	      }
	   }
	@Override
	@SuppressWarnings("unused")
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
	      if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
	         return ActionResult.resultPass(itemstack);
	      } else if (worldIn.isRemote) {
	         return ActionResult.resultSuccess(itemstack);
	      } else {
	         BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
	         BlockPos blockpos = blockraytraceresult.getPos();
	         if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
	            return ActionResult.resultPass(itemstack);
	         } else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
	            EntityType<?> entitytype = this.getType(itemstack.getTag());
	            Entity entity = entitytype.spawn(worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false);
	            entity.deserializeNBT(this.nbt);
	            System.out.println(this.nbt.getInt("SlabfishType"));
	            if (entity == null) {
	               return ActionResult.resultPass(itemstack);
	            } else {
	               if (!playerIn.abilities.isCreativeMode) {
	                  itemstack.shrink(1);
	               }

	               playerIn.addStat(Stats.ITEM_USED.get(this));
	               return ActionResult.resultSuccess(itemstack);
	            }
	         } else {
	            return ActionResult.resultFail(itemstack);
	         }
	      }
	   }
	
	public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      if (world.isRemote) {
	         return ActionResultType.SUCCESS;
	      } else {
	         ItemStack itemstack = context.getItem();
	         BlockPos blockpos = context.getPos();
	         Direction direction = context.getFace();
	         BlockState blockstate = world.getBlockState(blockpos);
	         Block block = blockstate.getBlock();
	         if (block == Blocks.SPAWNER) {
	            TileEntity tileentity = world.getTileEntity(blockpos);
	            if (tileentity instanceof MobSpawnerTileEntity) {
	               AbstractSpawner abstractspawner = ((MobSpawnerTileEntity)tileentity).getSpawnerBaseLogic();
	               EntityType<?> entitytype1 = this.getType(itemstack.getTag());
	               
	               abstractspawner.setEntityType(entitytype1);
	               tileentity.markDirty();
	               world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
	               itemstack.shrink(1);
	               return ActionResultType.SUCCESS;
	            }
	         }

	         BlockPos blockpos1;
	         if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
	            blockpos1 = blockpos;
	         } else {
	            blockpos1 = blockpos.offset(direction);
	         }

	         EntityType<?> entitytype = this.getType(itemstack.getTag());
	         Entity entity = entitytype.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
	         entity.deserializeNBT(entity.serializeNBT().merge(this.nbt));
	         System.out.println(this.nbt.getInt("CatType"));
	         if (entity != null && !context.getPlayer().abilities.isCreativeMode) {
	            itemstack.shrink(1);
	         }

	         return ActionResultType.SUCCESS;
	      }
	   }
	
	@OnlyIn(Dist.CLIENT)
   public int getColor(int tintIndex) {
      return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
   }

}
