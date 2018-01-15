package net.earthcomputer.clientcommands.command;

import net.earthcomputer.clientcommands.CreativeInventoryListener;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandCGive extends ClientCommandBase {

	@Override
	public String getName() {
		return "cgive";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/cgive <item> [count] [meta] [nbt]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		ensureCreativeMode();

		if (args.length < 1) {
			throw new WrongUsageException(getUsage(sender));
		}

		Entity executingEntity = sender.getCommandSenderEntity();
		if (!(executingEntity instanceof EntityPlayer)) {
			throw new CommandException("This command must be executed by a player");
		}
		EntityPlayer player = (EntityPlayer) executingEntity;

		Item item = getItemByText(sender, args[0]);
		int meta = args.length >= 3 ? parseInt(args[3]) : 0;

		ItemStack stack = new ItemStack(item, 1, meta);

		if (args.length >= 4) {
			try {
				stack.setTagCompound(JsonToNBT.getTagFromJson(buildString(args, 3)));
			} catch (NBTException e) {
				throw new CommandException("commands.give.tagError", e.getMessage());
			}
		}

		int count = args.length >= 2 ? parseInt(args[2], 1, stack.getMaxStackSize()) : 1;
		stack.setCount(count);

		boolean added = player.inventory.addItemStackToInventory(stack);
		if (added) {
			player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ITEM_PICKUP,
					SoundCategory.PLAYERS, 0.2f,
					((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7f + 1f) * 2f);
			CreativeInventoryListener listener = new CreativeInventoryListener();
			player.inventoryContainer.addListener(listener);
			player.inventoryContainer.detectAndSendChanges();
			player.inventoryContainer.removeListener(listener);
		}

		if (!added) {
			throw new CommandException("Your inventory is full");
		} else if (!stack.isEmpty()) {
			throw new CommandException("Failed to give you all the items");
		} else {
			sender.sendMessage(new TextComponentTranslation("commands.give.success", stack.getTextComponent(), count,
					player.getName()));
		}
	}

}
