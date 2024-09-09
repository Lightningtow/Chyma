////package lightningtow.chyma.integrations;
////
////import lightningtow.chyma.ChymaMain;
////import com.minenash.customhud.HudElements.supplier.BooleanSupplierElement;
////
////import static com.minenash.customhud.data.Flags.wrap;
////import static com.minenash.customhud.registry.CustomHudRegistry.registerElement;
////
////public class CustomhudIntegration {
////        public static void initCustomhud() {
////
////            // don't put log messages here, messages already in Main
////
////  todo this is commented because its done in JourneyMapIntegration? this is why we add comments before not touching the project for 6 months
////
////            registerElement("minimap_displayed", (f, c)
////                    ->  wrap(new BooleanSupplierElement(() -> ChymaMain.minimap_displayed), f));
////            registerElement("minimap", (f, c)
////                    ->  wrap(new BooleanSupplierElement(() -> ChymaMain.minimap_displayed), f));
////
////
////        }
////}
//package lightningtow.hudify.integrations;
//import com.minenash.customhud.HudElements.supplier.BooleanSupplierElement;
//import com.minenash.customhud.HudElements.supplier.NumberSupplierElement;
//import com.minenash.customhud.HudElements.supplier.SpecialSupplierElement;
//import com.minenash.customhud.HudElements.supplier.StringSupplierElement;
//
//import static lightningtow.hudify.util.SpotifyData.*;
//
///*
//TO SWITCH BETWEEN CUSTOMHUD VERSIONS:
//
//swap the dependencies in build.gradle
//swap customhud_api_label in gradle.properties
//comment the appropriate section below
//
//if switching to v4: comment the entire CustomhudBoolSupplier.java file
//if switching to v3: uncomment the entire CustomhudBoolSupplier.java file
//
// */
//
///* 4.0 */
//import com.minenash.customhud.data.Flags;
//
//import static com.minenash.customhud.data.Flags.wrap;
//import static com.minenash.customhud.registry.CustomHudRegistry.registerElement;
//public class CustomhudIntegration {
//    public static void initCustomhud() {
//
//        registerElement("sp_album_art", (f, c) -> new CustomhudIconExtender(g_album_art_identifier, f)  );
//
//            registerElement("minimap_displayed", (f, c) ->  wrap(new BooleanSupplierElement(() -> ChymaMain.minimap_displayed), f));
//            registerElement("minimap", (f, c)
//                    ->  wrap(new BooleanSupplierElement(() -> ChymaMain.minimap_displayed), f));
//
//    }
//}
