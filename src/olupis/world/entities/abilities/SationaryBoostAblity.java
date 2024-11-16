package olupis.world.entities.abilities;

import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;

public class SationaryBoostAblity  extends Ability{

    public SationaryBoostAblity(){

    }

    @Override
    public void update(Unit unit){
        if(unit.elevation == 0)return;
        if(unit.elevation == 1)return; //prevents moving while changing from either for stricter bats
        unit.apply(StatusEffects.unmoving, 1);

    }

}
