package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.bomb.NukeCustom;
import com.hbm.inventory.container.ContainerNukeCustom;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.bomb.TileEntityNukeCustom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GUINukeCustom extends GuiInfoContainer {
	
	private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gunBombSchematic.png");
	private TileEntityNukeCustom testNuke;
	
	public GUINukeCustom(InventoryPlayer invPlayer, TileEntityNukeCustom tedf) {
		super(new ContainerNukeCustom(invPlayer, tedf));
		testNuke = tedf;
		
		this.xSize = 176;
		this.ySize = 222;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer( int i, int j) {
		String name = this.testNuke.hasCustomInventoryName() ? this.testNuke.getInventoryName() : I18n.format(this.testNuke.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		String[] text;

		text = new String[] { TextFormatting.YELLOW + "Conventional Explosives (Level " + testNuke.tnt + "/" + Math.min(testNuke.tnt, NukeCustom.maxTnt) + ")",
				"Caps at " + NukeCustom.maxTnt,
				"N�-like above level 75",
				TextFormatting.ITALIC + "\"Goes boom\"" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 16, guiTop + 89, 18, 18, mouseX, mouseY, text);

		text = new String[] { TextFormatting.YELLOW + "Nuclear (Level " + testNuke.nuke + "/" + testNuke.getNukeAdj() + ")",
				"Requires TNT level 16",
				"Caps at " + NukeCustom.maxNuke,
				"Has fallout",
				TextFormatting.ITALIC + "\"Now I am become death, destroyer of worlds.\"" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 34, guiTop + 89, 18, 18, mouseX, mouseY, text);

		text = new String[] { TextFormatting.YELLOW + "Thermonuclear (Level " + testNuke.hydro + "/" + testNuke.getHydroAdj() + ")",
				"Requires nuclear level 100",
				"Caps at " + NukeCustom.maxHydro,
				"Reduces added fallout by salted stage by 75%",
				TextFormatting.ITALIC + "\"And for my next trick, I'll make",
				TextFormatting.ITALIC + "the island of Elugelab disappear!\"" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 52, guiTop + 89, 18, 18, mouseX, mouseY, text);

		text = new String[] { TextFormatting.YELLOW + "Antimatter (Level " + testNuke.amat + "/" + testNuke.getAmatAdj() + ")",
				"Requires nuclear level 50",
				"Caps at " + NukeCustom.maxAmat,
				TextFormatting.ITALIC + "\"Antimatter, Balefire, whatever.\"" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 70, guiTop + 89, 18, 18, mouseX, mouseY, text);

		text = new String[] { TextFormatting.YELLOW + "Salted (Level " + testNuke.dirty + "/" + Math.min(testNuke.dirty, 100) + ")",
				"Extends fallout of nuclear and",
				"thermonuclear stages",
				"Caps at 100",
				TextFormatting.ITALIC + "\"Not to be confused with tablesalt.\"" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 88, guiTop + 89, 18, 18, mouseX, mouseY, text);

		text = new String[] { TextFormatting.YELLOW + "Schrabidium (Level " + testNuke.schrab + "/" + testNuke.getSchrabAdj() + ")",
				"Requires nuclear level 50",
				"Caps at " + NukeCustom.maxSchrab,
				TextFormatting.ITALIC + "\"For the hundredth time,",
				TextFormatting.ITALIC + "you can't bypass these caps!\"" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 106, guiTop + 89, 18, 18, mouseX, mouseY, text);

		text = new String[] { TextFormatting.YELLOW + "Ice cream (Level unknown)",
				TextFormatting.ITALIC + "\"Probably not ice cream but the label came off.\"" };
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 142, guiTop + 89, 18, 18, mouseX, mouseY, text);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if(this.testNuke.euph > 0)
			drawTexturedModalRect(guiLeft + 142, guiTop + 89, 176, 108, 18, 18);
		else if(this.testNuke.schrab > 0)
			drawTexturedModalRect(guiLeft + 106, guiTop + 89, 176, 90, 18, 18);
		else if(this.testNuke.amat > 0)
			drawTexturedModalRect(guiLeft + 70, guiTop + 89, 176, 54, 18, 18);
		else if(this.testNuke.hydro > 0)
			drawTexturedModalRect(guiLeft + 52, guiTop + 89, 176, 36, 18, 18);
		else if(this.testNuke.nuke > 0)
			drawTexturedModalRect(guiLeft + 34, guiTop + 89, 176, 18, 18, 18);
		else if(this.testNuke.tnt > 0)
			drawTexturedModalRect(guiLeft + 16, guiTop + 89, 176, 0, 18, 18);

		if(this.testNuke.dirty > 0 && 
				this.testNuke.nuke > 0 &&
				this.testNuke.amat == 0 &&
				this.testNuke.schrab == 0 &&
				this.testNuke.euph == 0)
			drawTexturedModalRect(guiLeft + 142, guiTop + 89, 176, 108, 18, 18);
	}
}
