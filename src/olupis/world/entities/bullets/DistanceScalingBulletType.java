package olupis.world.entities.bullets;

import arc.math.*;
import arc.util.*;
import mindustry.*;
import mindustry.entities.bullet.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;

import static mindustry.Vars.state;

public class DistanceScalingBulletType extends ExplosionBulletType{
    //static final UnitDamageEvent bulletDamageEvent = new UnitDamageEvent();

    public float
    /* % min/max dmg depending on range*/
    minDmgMul = 0.25f, maxDmgMul = 1,
    /* clamps for dist */
    maxDst = Float.MAX_VALUE, minDst = Vars.tilesize * 2;


    public DistanceScalingBulletType(float splashDamage, float splashDamageRadius){
        this.splashDamage = splashDamage;
        this.splashDamageRadius = splashDamageRadius;
        rangeOverride = Math.max(rangeOverride, splashDamageRadius * 2f / 3f);
    }

    public DistanceScalingBulletType(){
        super();
    }


    @Override
    public float damageMultiplier(Bullet b){
        float distMul = minDmgMul;
        if(b.owner instanceof  Posc p ){
            float dst = Mathf.clamp(b.dst(p), minDst, maxDst);
            if(maxDst != Float.MAX_VALUE) dst /= maxDst;
            distMul = Mathf.lerp(minDmgMul, maxDmgMul, dst);
            Log.err( dst +  " " + distMul);
        }


        if(b.owner instanceof Unit u) return u.damageMultiplier() * state.rules.unitDamage(b.team) * distMul;
        if(b.owner instanceof Building) return state.rules.blockDamage(b.team) * distMul;

        return 1f;
    }
}
