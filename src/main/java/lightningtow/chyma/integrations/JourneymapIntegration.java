package lightningtow.chyma.integrations;

import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.display.*;
import journeymap.client.api.event.ClientEvent;
import lightningtow.chyma.ChymaMain;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.apache.logging.log4j.Level;
import java.util.EnumSet;
import java.util.Objects;

import static lightningtow.chyma.ChymaMain.LogThis;
import static lightningtow.chyma.ChymaMain.MOD_ID;

/**
 * see <a href="https://github.com/TeamJM/journeymap-api/blob/1.20.x_1.9-fabric/src/testmod/java/example/mod/client/plugin/ExampleJourneymapPlugin.java">...</a>
 * To prevent classloader errors if JourneyMap isn't loaded
 * (and thus the API classes aren't loaded), this class isn't referenced anywhere directly in the mod.
 * The @journeymap.client.api.IClientPlugin implementation makes this plugin class discoverable to JourneyMap,
 * which will create an instance of it and then call initialize on it.
 */
public class JourneymapIntegration implements IClientPlugin
{
    // see http://github.com/TeamJM/journeymap-api
    @Override
    public String getModId() { return ChymaMain.MOD_ID; } // Used by Journeymap to associate a modId with this plugin.

    private IClientAPI jmAPI = null;    // API reference

    // Called by JourneyMap during the init phase of mod loading.
    @Override
    public void initialize(final IClientAPI jmAPI)
    {
        // Set ClientProxy.SampleWaypointFactory with an implementation that uses the JourneyMap IClientAPI under the covers.
        this.jmAPI = jmAPI;

        // Subscribe to desired ClientEvent types from JourneyMap
//        this.jmAPI.subscribe(getModId(), EnumSet.of(DEATH_WAYPOINT, MAPPING_STARTED, MAPPING_STOPPED, REGISTRY));
        this.jmAPI.subscribe(getModId(), EnumSet.of(ClientEvent.Type.DISPLAY_UPDATE,
                ClientEvent.Type.MAPPING_STARTED, ClientEvent.Type.MAPPING_STOPPED));


		ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                jmAPI.removeAll(MOD_ID);


                ChymaMain.minimap_displayed = Objects.requireNonNull(jmAPI.getUIState(Context.UI.Minimap)).active;
                LogThis(Level.INFO, String.valueOf(Objects.requireNonNull(jmAPI.getUIState(Context.UI.Minimap))));
//            LogThis(Level.INFO, String.valueOf(Objects.requireNonNull(jmAPI.getUIState(Context.UI.Fullscreen))));
//            LogThis(Level.INFO, "=========================================================");
            }
            catch (Exception e) { LogThis(Level.ERROR, e.getMessage() + e); }
        });

//        LogThis(Level.INFO,"Initialized " + getClass().getName());
        LogThis(Level.INFO, "Successfully integrated with Journeymap");

    }


    /**
     * Called by JourneyMap on the main Minecraft thread when a {@link journeymap.client.api.event.ClientEvent} occurs.
     * Be careful to minimize the time spent in this method so you don't lag the game.
     * <p>
     * You must call {@link IClientAPI#subscribe(String, EnumSet)} at some point to subscribe to these events, otherwise this
     * method will never be called.
     * <p>
     * If the event type is {@link journeymap.client.api.event.ClientEvent.Type#DISPLAY_UPDATE},
     * this is a signal to {@link journeymap.client.api.IClientAPI#show(journeymap.client.api.display.Displayable)}
     * all relevant Displayables for the {@link journeymap.client.api.event.ClientEvent#dimension} indicated.
     * (Note: ModWaypoints with persisted==true will already be shown.)
     */
    @Override
    public void onEvent(ClientEvent event)
    {
        try
        {
            ChymaMain.minimap_displayed = Objects.requireNonNull(jmAPI.getUIState(Context.UI.Minimap)).active;
//            if (event.type == ClientEvent.Type.DISPLAY_UPDATE) {
//                ChymaMain.minimap_displayed = Objects.requireNonNull(jmAPI.getUIState(Context.UI.Minimap)).active;
//            }

        }
        catch (Throwable t) { LogThis(Level.ERROR, t.getMessage() + t); }
    }

}



