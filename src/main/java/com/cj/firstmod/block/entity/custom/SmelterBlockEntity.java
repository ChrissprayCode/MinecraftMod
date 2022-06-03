package com.cj.firstmod.block.entity.custom;

import java.awt.TextComponent;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.cj.firstmod.block.entity.BlockEntities;
import com.cj.firstmod.init.BlockInit;
import com.cj.firstmod.init.ItemInit;
import com.cj.firstmod.recipe.SmelterRecipe;
import com.cj.firstmod.screen.SmelterMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class SmelterBlockEntity extends BlockEntity implements MenuProvider{
	
	private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};
	
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
	
	//variables for progress of smelting
	protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
	

	public SmelterBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(BlockEntities.SMELTER_ENTITY.get(), p_155229_, p_155230_);
		
		//create a new container and pass in the current/max progess
		this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return SmelterBlockEntity.this.progress;
                    case 1: return SmelterBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: SmelterBlockEntity.this.progress = value; break;
                    case 1: SmelterBlockEntity.this.maxProgress = value; break;
                }
            }

            public int getCount() {
                return 2; //THIS MUST MATCH WHAT IS ON SMELTER MENU. IT'S HOW MUCH DATA YER PASSIN THROUGH
            }
        };
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		// TODO Auto-generated method stub
		return new SmelterMenu(p_39954_, p_39955_, this, this.data); //data is what displays the progress arrow
	}

	@Override
	public Component getDisplayName() {
		// TODO Auto-generated method stub
		return new net.minecraft.network.chat.TextComponent("Smelter");
	}

	
//IDK WHAT THIS STUFF DOES, IT'S ALL IMPORTED
	 @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) { //Makes it so other mods can interact with this (makes it compatible) 
	        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	            return lazyItemHandler.cast();
	        }

	        return super.getCapability(cap, side);
	    }

	    @Override //assigning the lazy item handler
	    public void onLoad() {
	        super.onLoad();
	        lazyItemHandler = LazyOptional.of(() -> itemHandler);
	    }

	    @Override
	    public void invalidateCaps()  {
	        super.invalidateCaps();
	        lazyItemHandler.invalidate();
	    }

	    @Override //Saves the inventory when leaving world
	    protected void saveAdditional(@NotNull CompoundTag tag) {
	        tag.put("inventory", itemHandler.serializeNBT());
	        tag.putInt("smelter.progress", progress); //Save smelting progress
	        super.saveAdditional(tag);
	    }

	    @Override //Saves the inventory when loading the world
	    public void load(CompoundTag nbt) {
	        super.load(nbt);
	        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
	        progress = nbt.getInt("smelter.progress"); //Load smelting progress
	    }

	    public void drops() { //Makes sure that all of the items inside the block are dropped when block is broken
	        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
	        for (int i = 0; i < itemHandler.getSlots(); i++) {
	            inventory.setItem(i, itemHandler.getStackInSlot(i));
	        }

	        Containers.dropContents(this.level, this.worldPosition, inventory);
	    }
	    
//MORE IMPORTS
	    
	    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SmelterBlockEntity pBlockEntity) {
	        if(hasRecipe(pBlockEntity)) {
	            pBlockEntity.progress++;
	            setChanged(pLevel, pPos, pState);
	            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
	                craftItem(pBlockEntity);
	            }
	        } else {
	            pBlockEntity.resetProgress();
	            setChanged(pLevel, pPos, pState);
	        }
	    }

	    private static boolean hasRecipe(SmelterBlockEntity entity) {
	        Level level = entity.level;
	        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
	        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
	            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
	        }

	        //IS THERE A VALID RECIPE WITH ITEMS PUT INTO THE SMLETER?
	        Optional<SmelterRecipe> match = level.getRecipeManager()
	                .getRecipeFor(SmelterRecipe.Type.INSTANCE, inventory, level);

	        //If there is a valid recipe, and you can insert an item into the output slot (Not over 64 items, and is no current item/same item is already in
//CHANGE THIS SO ITS &&LAVAISPRESENT
	        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
	                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
	    }

	    private static void craftItem(SmelterBlockEntity entity) {
	        Level level = entity.level;
	        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
	        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
	            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
	        }

	        //check again if the recipe is valid??
	        Optional<SmelterRecipe> match = level.getRecipeManager()
	                .getRecipeFor(SmelterRecipe.Type.INSTANCE, inventory, level);

	        //perform the craftin
	        if(match.isPresent()) {
	        	entity.itemHandler.extractItem(0, 1, false); //extract 1 item from first slot,
		        entity.itemHandler.extractItem(1, 3, false); //extract 1 item from second slot
		        entity.itemHandler.extractItem(2, 1, false); //extract 1 item from third slot
		        //entity.itemHandler.getStackInSlot(2).hurt(1, new Random(), null); //randomly hurt the tool in the third slot

		        entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
	                    entity.itemHandler.getStackInSlot(3).getCount() + 1));
		        entity.itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET)); //give the player their hard earned bucket back

	            entity.resetProgress();
	        }
	    }

	    private void resetProgress() {
	        this.progress = 0;
	    }
	    
	    
	    private static boolean containsLava(SmelterBlockEntity entity) {
	    	
	    	return entity.itemHandler.getStackInSlot(2).getItem() == Items.LAVA_BUCKET;
	    }


	    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
	        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
	    }

	    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
	        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
	    }
	    
	    /*
	    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SmelterBlockEntity pBlockEntity) {
	        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
	            craftItem(pBlockEntity);
	        }
	    }
	    
	    private static void craftItem(SmelterBlockEntity entity) {
	        entity.itemHandler.extractItem(0, 1, false); //extract 1 item from first slot,
	        entity.itemHandler.extractItem(1, 3, false); //extract 1 item from second slot
	        entity.itemHandler.extractItem(2, 1, false); //extract 1 item from third slot
	        //entity.itemHandler.getStackInSlot(2).hurt(1, new Random(), null); //randomly hurt the tool in the third slot

	        entity.itemHandler.setStackInSlot(3, new ItemStack(ItemInit.BRONZE_INGOT.get(),
	                entity.itemHandler.getStackInSlot(3).getCount() + 1)); //create a bronze ingot in the last slot, or just add 1 if one exists
	        entity.itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET)); //give the player their hard earned bucket back
	    }

	    //Set the recipe for the item
	    private static boolean hasRecipe(SmelterBlockEntity entity) {
	        boolean hasItemInWaterSlot = entity.itemHandler.getStackInSlot(0).getItem() == Items.LAVA_BUCKET;
	        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem() == Items.COPPER_INGOT;
	        boolean hasCorrectAmountInFirstSlot = entity.itemHandler.getStackInSlot(1).getCount() >= 3;
	        boolean hasItemInSecondSlot = entity.itemHandler.getStackInSlot(2).getItem() == ItemInit.TIN_INGOT.get();

	        return hasItemInWaterSlot && hasItemInFirstSlot && hasItemInSecondSlot && hasCorrectAmountInFirstSlot;
	    }

	    private static boolean hasNotReachedStackLimit(SmelterBlockEntity entity) {
	        return entity.itemHandler.getStackInSlot(3).getCount() < entity.itemHandler.getStackInSlot(3).getMaxStackSize();
	    }
	    */
	    

}
