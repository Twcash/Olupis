package olupis.world.entities.units;

import arc.math.*;
import mindustry.*;
import mindustry.gen.*;
import olupis.world.entities.parts.*;

import java.util.*;

public class DuckyTubeTankUnitType extends  LeggedWaterUnit{
    public float floatRate = 0.03f;
    HashMap<Unit, Float>floatingTracker = new HashMap<>(),
                                        treadTracker = new HashMap<>();

    public DuckyTubeTankUnitType(String name) {
        super(name);
    }


    @Override
    public void update(Unit unit){
        super.update(unit);
        if (unit.dead()){
            floatingTracker.remove(unit);
            treadTracker.remove(unit);
        }
        if(!floatingTracker.containsKey(unit)){
            floatingTracker.put(unit, onWater(unit) ? 1f : 0f);
            treadTracker.put(unit, 0f);
        }
        else{
            float prev = floatingTracker.get(unit), tar = onWater(unit) ? 1 : -1;
            floatingTracker.replace(unit, Mathf.clamp(prev + (tar * floatRate)));

            if (!unit.vel.isZero(0.001F) || Vars.net.client()) {
                float len = unit.deltaLen(), tprev = treadTracker.get(unit) ;
                //TODO: maybe make this a lerp but rushie too dumb to to figure it out atm
                treadTracker.replace(unit, tprev + len);
            }
        }

    }
    @Override
    public void updatePrams (Unit unit){
        NyfPartParms.nyfparams.set(
            unit.healthf(),
            unit.team.id,
            unit.elevation(),
            partAmmo(unit),
            floatingTracker.getOrDefault(unit, 0f),
            treadTracker.getOrDefault(unit, 0f)
        );
    }


    @Override
    public <T extends Unit&Tankc> void drawTank(T unit){
        super.drawTank(unit);
    }
}
