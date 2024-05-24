package lightningtow.chyma;

import lightningtow.chyma.integrations.CustomhudIntegration;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class ChymaMain implements ClientModInitializer
{
	public static final String MOD_ID = "chyma";
	public static final String MOD_DISPLAY_NAME = "Chyma";

	public static Boolean minimap_displayed = false;

	public static void LogThis(org.apache.logging.log4j.Level lvl, String msg) {
		msg = MOD_DISPLAY_NAME + " " + msg;
		LogManager.getLogger(MOD_DISPLAY_NAME).log(lvl, msg);
	}


	@Override
	public void onInitializeClient() {
		// journeymap initialization happens in JourneymapIntegration. It is not referenced elsewhere to leave it as a soft dependency

		try {
			CustomhudIntegration.initCustomhud();
			LogThis(Level.INFO, "Successfully integrated with CustomHud");

		} catch (Exception e) {
			LogThis(Level.ERROR, "Error integrating with CustomHud: " + e);
		}

	}

}
