package tsteelworks.common.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsteelworks.blocks.logic.DeepTankLogic;
import tsteelworks.blocks.logic.HighOvenDuctLogic;
import tsteelworks.blocks.logic.HighOvenLogic;
import tsteelworks.client.TSClientProxy;
import tsteelworks.client.gui.DeepTankGui;
import tsteelworks.client.gui.HighOvenDuctGui;
import tsteelworks.client.gui.HighOvenGui;
import tsteelworks.client.gui.TSManualGui;
import tsteelworks.inventory.DeepTankContainer;
import tsteelworks.inventory.HighOvenContainer;
import tsteelworks.inventory.HighOvenDuctContainer;

public class GuiHandler implements IGuiHandler {
	public static final int MANUAL_GUI_ID = -1;
	public static final int HIGHOVEN_GUI_ID = 0;
	public static final int HIGHOVEN_DUCT_GUI_ID = 1;
	public static final int DEEPTANK_GUI_ID = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case HIGHOVEN_GUI_ID:
				return new HighOvenGui(player.inventory, (HighOvenLogic) world.getTileEntity(x, y, z));
			case HIGHOVEN_DUCT_GUI_ID:
				return new HighOvenDuctGui(player.inventory, (HighOvenDuctLogic) world.getTileEntity(x, y, z));
			case DEEPTANK_GUI_ID:
				return new DeepTankGui(player.inventory, (DeepTankLogic) world.getTileEntity(x, y, z));
			case MANUAL_GUI_ID:
				final ItemStack stack = player.getCurrentEquippedItem();
				return new TSManualGui(stack, TSClientProxy.getManualFromStack(stack));
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case HIGHOVEN_DUCT_GUI_ID:
				return new HighOvenDuctContainer(player.inventory, (HighOvenDuctLogic) world.getTileEntity(x, y, z));
			case HIGHOVEN_GUI_ID:
				return new HighOvenContainer(player.inventory, (HighOvenLogic) world.getTileEntity(x, y, z));
			case DEEPTANK_GUI_ID:
				return new DeepTankContainer(player.inventory, (DeepTankLogic) world.getTileEntity(x, y, z));
			case MANUAL_GUI_ID:
				final ItemStack stack = player.getCurrentEquippedItem();
				return new TSManualGui(stack, TSClientProxy.getManualFromStack(stack));
		}

		return null;
	}
}