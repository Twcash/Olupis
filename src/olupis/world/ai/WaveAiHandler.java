package olupis.world.ai;

import arc.math.*;
import arc.util.*;
import mindustry.*;
import mindustry.ai.types.*;
import mindustry.entities.units.*;
import olupis.world.entities.units.*;

public class WaveAiHandler extends AIController{
    public int unitAiType = 0;

    //yes this whole class just for this
    public WaveAiHandler() {

    }

    //Todo in v8 `easy` campain means this randomness is off
    public WaveAiHandler(int type) {
        unitAiType = type;
    }


    @Override
    public void init(){
        if(unit.type instanceof NyfalisUnitType nyf){
            int types = 0;
            if(nyf.waveHunts) types += 1;
            if(nyf.canCircleTarget) types += 1;
            if(nyf.canGuardUnits) types += 1;

            unitAiType = Mathf.random(0, types);
        }
    }

    @Nullable
    @Override
    public AIController fallback() {
        if(unit.type instanceof NyfalisUnitType nyf){
            switch(unitAiType){ //anyone who has better idea for random ai picker feel free
                case 3:{ //guard 1st
                    guardAi(nyf);
                    if(aiNotSet())huntingAi(nyf);
                    else if(aiNotSet())circleAi(nyf);

                    if(!aiNotSet()) return fallback;
                }
                case 2: { //circle 1st
                    circleAi(nyf);
                    if(aiNotSet())guardAi(nyf);
                    else if(aiNotSet())huntingAi(nyf);

                    if(!aiNotSet()) return fallback;
                };
                case 1: { //hunt 1st
                    huntingAi(nyf);
                    if(aiNotSet())circleAi(nyf);
                    else if(aiNotSet())guardAi(nyf);

                    if(!aiNotSet()) return fallback;
                }
                case 0:
                    FlyingAI ai = new FlyingAI();
                    fallback = ai;
                    return ai;
            }

        }
        return null;
    }

    @Override
    public boolean useFallback(){ /*allowed to be used in waves*/
        return unit.team.data().hasAI() || unit.team.isOnlyAI() || unit.team == Vars.state.rules.waveTeam;
    }

    public boolean aiNotSet(){
        return fallback == null;
    }

    public void huntingAi(NyfalisUnitType nyf){
        if(nyf.waveHunts){
            SearchAndDestroyFlyingAi ai = new SearchAndDestroyFlyingAi();
            ai.updateTargeting = true;
            fallback = ai;
        }
    }

    public void circleAi(NyfalisUnitType nyf){
        if(nyf.canCircleTarget){
            AgressiveFlyingAi ai = new AgressiveFlyingAi();
            ai.shouldCircle = true;
            fallback = ai;
        }
    }

    public void guardAi(NyfalisUnitType nyf){
        if (nyf.canGuardUnits){
            ArmDefenderAi ai = new ArmDefenderAi();
            fallback = ai;
        }
    }



}
