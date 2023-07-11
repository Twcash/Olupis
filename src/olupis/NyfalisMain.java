package olupis;

import arc.Core;
import arc.Events;
import arc.scene.Group;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.game.Team;
import mindustry.gen.Icon;
import mindustry.mod.Mod;
import mindustry.type.Planet;
import mindustry.ui.Styles;
import olupis.content.*;
import olupis.input.NyfalisSettingsDialog;
import olupis.input.NyfalisSounds;
import olupis.world.planets.NyfalisTechTree;

import java.util.concurrent.atomic.AtomicBoolean;

import static mindustry.Vars.*;
import static olupis.content.NyfalisPlanets.*;

public class NyfalisMain extends Mod{
    public NyfalisSounds soundHandler = new NyfalisSounds();
    public NyfalisSettingsDialog nyfalisSettings;

    @Override
    public void loadContent(){
        NyfalisItemsLiquid.LoadItems();
        NyfalisItemsLiquid.LoadLiquids();
        NyfalisUnits.LoadUnits();
        NyfalisBlocks.LoadWorldTiles();
        NyfalisBlocks.LoadBlocks();
        NyfalisSchematic.LoadSchematics();
        NyfalisPlanets.LoadPlanets();
        NyfalisSectors.LoadSectors();
        NyfalisSounds.LoadMusic();

        NyfalisPlanets.PostLoadPlanet();
        NyfalisTechTree.load();
        NyfalisBlocks.AddAttributes();
        NyfalisUnits.PostLoadUnits();

        Log.info("OwO, Nyfalis (Olupis) content Loaded! Hope you enjoy nya~");
    }

    public NyfalisMain(){
        if(headless)return;

        Events.on(ClientLoadEvent.class, e -> {
            NyfalisBlocks.NyfalisBlocksPlacementFix();
            NyfalisSettingsDialog.AddNyfalisSoundSettings();

            Vars.ui.planet.shown(() -> {
                if(Core.settings.getBool("olupis-space-sfx")) {Core.audio.play(NyfalisSounds.space, Core.settings.getInt("ambientvol", 100) / 100f, 0, 0, false);}
            });

            /*For those people who don't like the name/icon or overwrites in general*/
            if(Core.settings.getBool("olupis-green-icon")) Team.green.emoji = "\uf7a6";
            if(Core.settings.getBool("olupis-green-name")) Team.green.name = "olupis-green";
        });
        Events.on(EventType.WorldLoadEvent.class, l ->{
            //debug and if someone needs to convert a map and said map does not have the Nyfalis Block set
            if( Core.settings.getBool("olupis-debug")) buildDebugUI(Vars.ui.hudGroup);

            /*avoids Nyfalis stuff on serpulo Ex: leadpipes*/
            /*TODO: See if we can hide this in build visibility instead*/
            if(shouldAutoBan()){
                if(state.rules.blockWhitelist){
                    NyfalisBlocks.nyfalisBuildBlockSet.each(b -> state.rules.bannedBlocks.remove(b));
                }else NyfalisBlocks.nyfalisBuildBlockSet.each(b -> state.rules.bannedBlocks.add(b));
            }
            soundHandler.replaceSoundHandler();
        });

    }

    public boolean shouldAutoBan(){
        if(!Core.settings.getBool("olupis-auto-ban")) return false;
        AtomicBoolean hasCore = new AtomicBoolean(false);
        NyfalisBlocks.nyfalisCores.each(c ->{if (state.stats.placedBlockCount.get(c, 0) >= 1) hasCore.set(true);});

        if(hasCore.get())return false;
        if(state.rules.env == defaultEnv) return false;
        if(state.isCampaign()){ Planet sector = state.getSector().planet;
            if(sector == arthin) return false;
            if(sector == spelta) return false;
            return sector != nyfalis;
        }

        return false;
    }

    public static void buildDebugUI(Group group){
        group.fill(t -> {
            t.visible(() -> Vars.ui.hudfrag.shown);
            t.bottom().left();
            t.button("Export w/ Nyfalis", Icon.file, Styles.squareTogglet, () -> {
                NyfalisPlanets.nyfalis.applyRules(state.rules);
                ui.paused.show();
            }).width(155f).height(50f).margin(12f).checked(false);
        });
    }


    @Override
    public void init() {
        nyfalisSettings = new NyfalisSettingsDialog();
    }


}
