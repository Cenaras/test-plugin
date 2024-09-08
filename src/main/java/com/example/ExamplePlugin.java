package com.example;

import com.example.entities.MonsterInfo;
import com.example.entities.PlayerInfo;
import com.example.ui.TestPanel;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ScriptID;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.Map;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject private CollectionLog collectionLog;

	@Inject
	private ExampleConfig config;

	@Inject
	private ClientToolbar clientToolBar;

	private PlayerInfo playerInfo = new PlayerInfo();

	private static final BufferedImage ICON = ImageUtil.loadImageResource(ExamplePlugin.class, "example.png");

	@Override
	protected void startUp() {
		Map<Integer, MonsterInfo> monsterInfo = MonsterInfo.parseMonsters();

		log.info("Example started!");
		TestPanel panel = new TestPanel(monsterInfo, playerInfo);
		NavigationButton navButton = NavigationButton.builder()
				.tooltip("Iron DPS Calc")
				.icon(ICON)
				.priority(6)
				.panel(panel)
				.build();


		clientToolBar.addNavigation(navButton);


	}

	@Override
	protected void shutDown() throws Exception
	{

		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
//		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
//		{
//			// TODO - cache
//		}

		playerInfo.updateStats(client.getRealSkillLevels());
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widget) {
		if (widget.getGroupId() == InterfaceID.COLLECTION_LOG) {
			System.out.println("You have opened the collection log!");
		}
	}

	@Subscribe
	public void onScriptPostFired(ScriptPostFired scriptPostFired)
	{
		if (scriptPostFired.getScriptId() == ScriptID.COLLECTION_DRAW_LIST)
		{
			// TODO: This is invoked each time the log is drawn and there is no reason to do that.
			// But for now this is fine for now to iterate all items and see if they are unlocked or not...
			clientThread.invokeLater(() -> collectionLog.updateAllCollectionLogItems(this.playerInfo));
		}
	}

	@Provides
	ExampleConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExampleConfig.class);
	}
}
