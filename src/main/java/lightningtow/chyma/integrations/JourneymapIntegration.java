package lightningtow.chyma.integrations;

import com.minenash.customhud.HudElements.interfaces.HudElement;
import com.minenash.customhud.HudElements.supplier.BooleanSupplierElement;
import com.minenash.customhud.data.Flags;
import journeymap.api.v2.client.IClientAPI;
import journeymap.api.v2.client.IClientPlugin;
import journeymap.api.v2.client.JourneyMapPlugin;
import journeymap.api.v2.client.display.Context;
import com.minenash.customhud.registry.CustomHudRegistry;


/**  To prevent classloader errors if JourneyMap isn't loaded
 *   (and thus the API classes aren't loaded), this class isn't referenced anywhere directly in the mod.
 *   The @journeymap.client.api.IClientPlugin implementation makes this plugin class discoverable to JourneyMap,
 *   which will create an instance of it and then call initialize on it.     */
@JourneyMapPlugin(apiVersion = "2.0.0")
public class JourneymapIntegration implements IClientPlugin
{
    public static final String MOD_ID = "chyma";
    public static final String MOD_DISPLAY_NAME = "Chyma";

//	public static Boolean minimap_displayed = false;

    // Logger.log doesn't add mod id to the message in a non-dev environment, this adds the mod id without having to add it every time
    public static void LogThis(String msg) { LogThis(org.apache.logging.log4j.Level.INFO, msg); } // 'default' parameter cause java is dumb
    public static void LogThis(org.apache.logging.log4j.Level lvl, String msg) {
        msg = "(" + MOD_DISPLAY_NAME + ") " + msg;
        org.apache.logging.log4j.LogManager.getLogger(MOD_DISPLAY_NAME).log(lvl, msg);
    }

    private IClientAPI jmAPI = null;


// https://github.com/TeamJM/journeymap-api/blob/1.20.x_1.9-fabric/src/testmod/java/example/mod/client/plugin/ExampleJourneymapPlugin.java
    @Override    // see http://github.com/TeamJM/journeymap-api
//    public String getModId() { return ChymaMain.MOD_ID; } // Used by Journeymap to associate a modId with this plugin.
    public String getModId() { return "chyma"; } // Used by Journeymap to associate a modId with this plugin.

    @Override   // Called by JourneyMap during the init phase of mod loading.
    public void initialize(final IClientAPI jmAPI)
    {
//		ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            UIState state = jmAPI.getUIState(Context.UI.Minimap);
//            ChymaMain.minimap_displayed = (state != null && state.active);
//        });
        this.jmAPI = jmAPI;

        try {
            HudElement elem = new BooleanSupplierElement(() -> {
                var state = jmAPI.getUIState(Context.UI.Minimap);
                return (state != null && state.active);
            });
            CustomHudRegistry.registerElement("jm", (f, c) -> Flags.wrap(elem, f));
            CustomHudRegistry.registerElement("jm_map", (f, c) -> Flags.wrap(elem, f));
            CustomHudRegistry.registerElement("jm_minimap", (f, c) -> Flags.wrap(elem, f));
//            LogThis(Level.INFO, "Successfully registered Journeymap variables with CustomHud");
        }
//        catch (Exception e) { LogThis(Level.ERROR, "Could not register Journeymap variables with CustomHud"); }
        catch (Exception e) {  }

//        this.jmAPI.subscribe(getModId(), EnumSet.of(DISPLAY_UPDATE));

    }
    /** Called by JourneyMap on the main Minecraft thread when a {journeymap.client.api.event.ClientEvent} occurs.
     * Be careful to minimize the time spent in this method so you don't lag the game.
     * You must call `jmAPI.subscribe(getModId(), EnumSet.of(DISPLAY_UPDATE))` at some point to subscribe to these events,
     * otherwise this method will never be called.       */
//all jm events: DISPLAY_UPDATE, MAPPING_STARTED, MAPPING_STOPPED, WAYPOINT, DEATH_WAYPOINT, MAP_CLICKED, MAP_DRAGGED, MAP_MOUSE_MOVED
//    @Override
//    public void onEvent(ClientEvent event) {} // this func needs to exist, it can be empty tho
}



