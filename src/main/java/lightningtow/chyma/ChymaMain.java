package lightningtow.chyma;

import com.minenash.customhud.HudElements.interfaces.HudElement;
import com.minenash.customhud.HudElements.supplier.BooleanSupplierElement;
import com.minenash.customhud.data.Flags;
import com.minenash.customhud.registry.CustomHudRegistry;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.Level;

public class ChymaMain implements ClientModInitializer
{
	public static final String MOD_ID = "chyma";
	public static final String MOD_DISPLAY_NAME = "Chyma";

	public static void LogThis(String msg) { LogThis(org.apache.logging.log4j.Level.INFO, msg); } // 'default' parameter cause java is dumb
	public static void LogThis(org.apache.logging.log4j.Level lvl, String msg) {
		msg = "(" + MOD_DISPLAY_NAME + ") " + msg;
		org.apache.logging.log4j.LogManager.getLogger(MOD_DISPLAY_NAME).log(lvl, msg); }


	public static boolean mapVisible = true;


	@Override
	public void onInitializeClient() {
		// journeymap initialization happens in JourneymapIntegration. It is not referenced elsewhere to leave it as a soft dependency
		try {

			HudElement elem = new BooleanSupplierElement(() -> (ChymaMain.mapVisible));

			CustomHudRegistry.registerElement("jm", (f, c) -> Flags.wrap(elem, f));
			CustomHudRegistry.registerElement("jm_map", (f, c) -> Flags.wrap(elem, f));
			CustomHudRegistry.registerElement("jm_minimap", (f, c) -> Flags.wrap(elem, f));
			LogThis("Successfully registered Journeymap variables with CustomHud");
		}
		catch (Exception e) { LogThis(Level.ERROR, "Could not register Journeymap variables with CustomHud: " + e.getMessage()); }


	}

}
