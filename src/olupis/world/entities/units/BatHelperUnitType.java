package olupis.world.entities.units;

import arc.*;
import arc.graphics.g2d.*;
import arc.struct.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.type.*;

public class BatHelperUnitType extends UnitType {
    public UnitType main = UnitTypes.alpha;
    public Seq<StatusEffect> blacklist = Seq.with(StatusEffects.unmoving, StatusEffects.disarmed, StatusEffects.invincible);

    public BatHelperUnitType(String name){
        super(name);
        constructor = main.constructor;
        flying = true;
    }

    public BatHelperUnitType(UnitType main){
        this(main.name + "-air");
        this.main = main;
    }


    public BatHelperUnitType(String name, UnitType main){
        this(name);
        this.main = main;
    }

    @Override
    public void load() {
        super.load();
        TextureRegion i = main.uiIcon;
        fullIcon = main.fullIcon;
        localizedName = main.localizedName + " " + Core.bundle.get("nyfalis-helper-air");
    }

        @Override
    public void update(Unit unit){
        super.update(unit);

        Unit u = this.main.create(unit.team);
        u.stack = unit.stack;

        for (StatusEffect e : Vars.content.statusEffects()) {
            if(unit.hasEffect(e)){
                boolean enemy = !blacklist.contains(e) && (unit.team == Vars.state.rules.waveTeam);
                u.apply(e, enemy  ? 999999f : u.getDuration(e));
            };
        }

        u.set(unit.x, unit.y);
        if(!Vars.net.client()){
            u.add();
        }
        unit.remove();
    }
}
