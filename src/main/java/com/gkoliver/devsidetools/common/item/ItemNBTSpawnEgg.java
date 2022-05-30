package com.gkoliver.devsidetools.common.item;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nullable;

//import com.farcr.swampexpansion.core.registry.SwampExEntities;
import com.gkoliver.devsidetools.core.registry.DevsideToolsItems;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.FluidModel;

public class ItemNBTSpawnEgg extends Item {
	public CompoundTag nbt;
	private final int primaryColor;
	private final int secondaryColor;
	private final EntityType<?> typeIn;

	public ItemNBTSpawnEgg(EntityType<?> typeIn, int primaryColorIn, int secondaryColorIn, Item.Properties builder, CompoundTag nbt) {
		super(builder);
		this.typeIn = typeIn;
		this.primaryColor = primaryColorIn;
		this.secondaryColor = secondaryColorIn;
		this.nbt = nbt;
		DevsideToolsItems.NBT_SPAWN_EGGS.add(this);
	}

	public boolean isSlabber = false;

	public ItemNBTSpawnEgg(int primaryColorIn, int secondaryColorIn, Properties builder, CompoundTag nbt) {
		this(null, primaryColorIn, secondaryColorIn, builder, nbt);
		isSlabber = true;
	}

	public EntityType<?> getType(@Nullable CompoundTag p_208076_1_) {
		if (isSlabber) {
			// return SwampExEntities.SLABFISH.get();
		}
		//else {
		return this.typeIn;
		//}
	}


	/*public InteractionResult useOn(UseOnContext context) {
	      Level world = context.getLevel();
	      if (world.isClientSide) {
	         return InteractionResult.SUCCESS;
	      } else {
	         ItemStack itemstack = context.getItemInHand();
	         BlockPos blockpos = context.getClickedPos();
	         Direction direction = context.getClickedFace();
	         BlockState blockstate = world.getBlockState(blockpos);
	         Block block = blockstate.getBlock();
	         if (block == Blocks.SPAWNER) {
	            BlockEntity tileentity = world.getBlockEntity(blockpos);
	            if (tileentity instanceof SpawnerBlockEntity) {
					SpawnerBlockEntity abstractspawner = ((SpawnerBlockEntity)tileentity).getSpawnerBaseLogic();
	               EntityType<?> entitytype1 = this.getType(itemstack.getTag());
	               
	               abstractspawner.getSpawner().setEntityId(entitytype1);
	               tileentity.setChanged();
	               world.markAndNotifyBlock(blockpos, context.getLevel().getChunkAt(blockpos), blockstate, blockstate, 3,1);
	               itemstack.shrink(1);
	               return ActionResultType.SUCCESS;
	            }
	         }

	         BlockPos blockpos1;
	         if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
	            blockpos1 = blockpos;
	         } else {
	            blockpos1 = blockpos.offset(context.getClickedFace().getNormal());
	         }
			  if (world instanceof ServerLevel) {
				  EntityType<?> entitytype = this.getType(itemstack.getTag());
				  Entity entity = entitytype.spawn((ServerLevel) world, itemstack, context.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
				  entity.deserializeNBT(entity.serializeNBT().merge(this.nbt));
				  System.out.println(this.nbt.getInt("CatType"));
				  if (entity != null && !context.getPlayer().isCreative()) {
					  itemstack.shrink(1);
				  }
			  }
	         return InteractionResult.SUCCESS;
	      }
	   }
	*/
	public InteractionResultHolder<ItemStack> use(Level p_43225_, Player p_43226_, InteractionHand p_43227_) {
		ItemStack itemstack = p_43226_.getItemInHand(p_43227_);
		HitResult hitresult = getPlayerPOVHitResult(p_43225_, p_43226_, ClipContext.Fluid.SOURCE_ONLY);
		if (hitresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else if (!(p_43225_ instanceof ServerLevel)) {
			return InteractionResultHolder.success(itemstack);
		} else {
			BlockHitResult blockhitresult = (BlockHitResult)hitresult;
			BlockPos blockpos = blockhitresult.getBlockPos();
			if (!(p_43225_.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
				return InteractionResultHolder.pass(itemstack);
			} else if (p_43225_.mayInteract(p_43226_, blockpos) && p_43226_.mayUseItemAt(blockpos, blockhitresult.getDirection(), itemstack)) {
				EntityType<?> entitytype = this.getType(itemstack.getTag());
				Entity e = entitytype.spawn((ServerLevel)p_43225_, itemstack, p_43226_, blockpos, MobSpawnType.SPAWN_EGG, false, false);

				if (e == null) {
					return InteractionResultHolder.pass(itemstack);
				} else {
					System.out.println(this.nbt.toString());
					e.deserializeNBT(this.nbt);
					if (!p_43226_.getAbilities().instabuild) {
						itemstack.shrink(1);
					}

					p_43226_.awardStat(Stats.ITEM_USED.get(this));
					p_43225_.gameEvent(GameEvent.ENTITY_PLACE, p_43226_);
					return InteractionResultHolder.consume(itemstack);
				}
			} else {
				return InteractionResultHolder.fail(itemstack);
			}
		}
	}
	public InteractionResult useOn(UseOnContext p_43223_) {
		Level level = p_43223_.getLevel();
		if (!(level instanceof ServerLevel)) {
			return InteractionResult.SUCCESS;
		} else {
			ItemStack itemstack = p_43223_.getItemInHand();
			BlockPos blockpos = p_43223_.getClickedPos();
			Direction direction = p_43223_.getClickedFace();
			BlockState blockstate = level.getBlockState(blockpos);
			if (blockstate.is(Blocks.SPAWNER)) {
				BlockEntity blockentity = level.getBlockEntity(blockpos);
				if (blockentity instanceof SpawnerBlockEntity) {
					BaseSpawner basespawner = ((SpawnerBlockEntity)blockentity).getSpawner();
					EntityType<?> entitytype1 = this.getType(itemstack.getTag());

					CompoundTag stupid = this.nbt.copy();
					stupid.putString("id", this.getType(itemstack.getTag()).getRegistryName().toString());
					CompoundTag finalI = new CompoundTag();
					finalI.put("entity", stupid);
					CompoundTag orig = blockentity.serializeNBT();
					System.out.println(orig);
					orig.put("SpawnData", finalI);
					System.out.println(orig);
					System.out.println(orig.getShort("Delay"));
					System.out.println(orig);
					blockentity.load(orig);
					System.out.println(blockentity.serializeNBT());
					basespawner.load(level, blockpos, orig);
					basespawner.setNextSpawnData(level, blockpos, new SpawnData(stupid, Optional.empty()));
					System.out.println(basespawner.save(new CompoundTag()));
					blockentity.setChanged();
					level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
					itemstack.shrink(1);
					return InteractionResult.CONSUME;
				}
			}

			BlockPos blockpos1;
			if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.relative(direction);
			}

			EntityType<?> entitytype = this.getType(itemstack.getTag());
			Entity e = entitytype.spawn((ServerLevel)level, itemstack, p_43223_.getPlayer(), blockpos1, MobSpawnType.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
			if (e != null) {
				e.deserializeNBT(e.serializeNBT().merge(nbt));
				itemstack.shrink(1);
				level.gameEvent(p_43223_.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
			}

			return InteractionResult.CONSUME;
		}
	}
	@OnlyIn(Dist.CLIENT)
   public int getColor(int tintIndex) {
      return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
   }

}
