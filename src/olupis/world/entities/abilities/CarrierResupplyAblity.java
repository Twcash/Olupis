package olupis.world.entities.abilities;

import mindustry.entities.abilities.*;

public class CarrierResupplyAblity extends Ability{
    public int tier = 1;

    public CarrierResupplyAblity(){
        //Empty for now
        //TODO: if ammo rule is on, they resupply all units in range
        display = false;
    }

    public CarrierResupplyAblity(int tier){
        this.tier = tier;
        display = false;
    }

}
