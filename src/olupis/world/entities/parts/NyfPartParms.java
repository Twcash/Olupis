package olupis.world.entities.parts;

import mindustry.entities.part.DrawPart;

public class NyfPartParms {
    public static final NyfPartParms.NyfPartParams nyfparams = new NyfPartParms.NyfPartParams();

    public static class NyfPartParams{
        public int team;
        public float health,elevation, ammo, floating, treads;

        public NyfPartParams set(float health, int team, float elevation, float ammo, float floating, float treads){
            this.health = health;
            this.team = team;
            this.elevation = elevation;
            this.ammo = ammo;
            this.floating = floating;
            this.treads = treads;

            return this;
        }

        public NyfPartParams set(float health, int team, float elevation, float ammo){
            return set(health, team, elevation, ammo, 0, 0);
        }
    }

    public interface NyfPartProgress {
        NyfPartProgress
            team = p -> nyfparams.team,
            elevation = p -> nyfparams.elevation,
            ammo = p -> nyfparams.ammo,
            floating = p -> nyfparams.floating,
            treads = p -> nyfparams.treads
        ;

        DrawPart.PartProgress
                elevationP = p-> nyfparams.elevation,
                floatingP = p-> nyfparams.floating,
                treadsP = p-> nyfparams.treads
        ;

        float get(NyfPartParams p);
    }

}
