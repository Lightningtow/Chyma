package lightningtow.chyma.integrations;
import journeymap.api.v2.client.IClientAPI;
import journeymap.api.v2.client.IClientPlugin;
import journeymap.api.v2.client.JourneyMapPlugin;
import journeymap.api.v2.client.display.Context;
import journeymap.api.v2.client.util.UIState;
import journeymap.api.v2.common.event.impl.ClientEvent;
import lightningtow.chyma.ChymaMain;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

/**  To prevent classloader errors if JourneyMap isn't loaded
 *   (and thus the API classes aren't loaded), this class isn't referenced anywhere directly in the mod.
 *   The @journeymap.client.api.IClientPlugin implementation makes this plugin class discoverable to JourneyMap,
 *   which will create an instance of it and then call initialize on it.     */
@JourneyMapPlugin(apiVersion = "2.0.0")
public class JourneymapIntegration implements IClientPlugin {

    @Override    // see http://github.com/TeamJM/journeymap-api
    public String getModId() { return ChymaMain.MOD_ID; } // Used by Journeymap to associate a modId with this plugin.

    @Override   // Called by JourneyMap during the init phase of mod loading.
    public void initialize(final IClientAPI jmAPI)
    {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
            UIState state = jmAPI.getUIState(Context.UI.Minimap);
            ChymaMain.mapVisible = (state != null && state.active);
        });
//        this.jmAPI.subscribe(getModId(), EnumSet.of(DISPLAY_UPDATE));
    }



}



