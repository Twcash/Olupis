package olupis.world.consumer;

import arc.struct.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.consumers.*;
import olupis.content.*;

public class ConsumeLubricant extends ConsumeCoolant {
    Seq<Liquid> allowedCoolants = Seq.with(Liquids.oil, NyfalisItemsLiquid.lubricant);

    public ConsumeLubricant(float amount){
        super(amount);
        this.filter = liquid -> allowedCoolants.contains(liquid);
    }
}
