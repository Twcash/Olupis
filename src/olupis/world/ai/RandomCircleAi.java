package olupis.world.ai;

import arc.math.*;
import arc.util.*;

import static mindustry.Vars.state;

public class RandomCircleAi extends  AgressiveFlyingAi{

    //yes this whole class just for this
    public RandomCircleAi() {

    }

    @Override
    public void init(){
        super.init();

        if(state.rules.waves && unit.team == state.rules.waveTeam){
            boolean owo  = Mathf.randomSeed(unit.id, 0, 1) == 1;
            Log.err(owo + "");

            this.shouldCircle = owo;
        }
    }
}
