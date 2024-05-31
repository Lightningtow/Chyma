package lightningtow.chyma.integrations;

import com.minenash.customhud.HudElements.interfaces.HudElement;
import com.minenash.customhud.HudElements.supplier.BooleanSupplierElement;
import com.minenash.customhud.data.Flags;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.display.*;
import journeymap.client.api.event.ClientEvent;
import lightningtow.chyma.ChymaMain;
import org.apache.logging.log4j.Level;
import java.util.EnumSet;
import com.minenash.customhud.registry.CustomHudRegistry;

import static lightningtow.chyma.ChymaMain.LogThis;

/**  To prevent classloader errors if JourneyMap isn't loaded
 *   (and thus the API classes aren't loaded), this class isn't referenced anywhere directly in the mod.
 *   The @journeymap.client.api.IClientPlugin implementation makes this plugin class discoverable to JourneyMap,
 *   which will create an instance of it and then call initialize on it.     */

public class JourneymapIntegration implements IClientPlugin
{
// https://github.com/TeamJM/journeymap-api/blob/1.20.x_1.9-fabric/src/testmod/java/example/mod/client/plugin/ExampleJourneymapPlugin.java
    @Override    // see http://github.com/TeamJM/journeymap-api
    public String getModId() { return ChymaMain.MOD_ID; } // Used by Journeymap to associate a modId with this plugin.

    @Override   // Called by JourneyMap during the init phase of mod loading.
    public void initialize(final IClientAPI jmAPI)
    {
//		ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            UIState state = jmAPI.getUIState(Context.UI.Minimap);
//            ChymaMain.minimap_displayed = (state != null && state.active);
//        });
        try {
            HudElement e = new BooleanSupplierElement(() -> {
                var state = jmAPI.getUIState(Context.UI.Minimap);
                return (state != null && state.active);
            });
            CustomHudRegistry.registerElement("minimap", (f, c) -> Flags.wrap(e, f));
            CustomHudRegistry.registerElement("minimap_displayed", (f, c) -> Flags.wrap(e, f));
            LogThis(Level.INFO, "Successfully registered Journeymap variables with CustomHud");
        }
        catch (Exception e) { LogThis(Level.ERROR, "Could not register Journeymap variables with CustomHud"); }
//        this.jmAPI.subscribe(getModId(), EnumSet.of(DISPLAY_UPDATE));

    }
    /** Called by JourneyMap on the main Minecraft thread when a {journeymap.client.api.event.ClientEvent} occurs.
     * Be careful to minimize the time spent in this method so you don't lag the game.
     * You must call `jmAPI.subscribe(getModId(), EnumSet.of(DISPLAY_UPDATE))` at some point to subscribe to these events,
     * otherwise this method will never be called.       */
//all jm events: DISPLAY_UPDATE, MAPPING_STARTED, MAPPING_STOPPED, WAYPOINT, DEATH_WAYPOINT, MAP_CLICKED, MAP_DRAGGED, MAP_MOUSE_MOVED
    @Override
    public void onEvent(ClientEvent event) {} // this func needs to exist, it can be empty tho
}



