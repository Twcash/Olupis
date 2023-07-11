package olupis.content;

import arc.graphics.Color;
import arc.struct.*;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.LiquidBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootSummon;
import mindustry.gen.Sounds;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.MessageBlock;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import olupis.world.blocks.*;
import olupis.world.entities.bullets.NoBoilLiquidBulletType;

import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.oil;
import static mindustry.type.ItemStack.with;
import static olupis.content.NyfalisAttribute.*;
import static olupis.content.NyfalisItemsLiquid.*;
import static olupis.content.NyfalisUnits.*;

public class NyfalisBlocks {
    //TODO: Woof woof wants  different class for loading turrets content so this isn't 1400~ lines long, pain & debating if should do
    //region Blocks Variables
    public static Block
        //environment
        /*Trees*/
        nyfalisTree, mossTree, pinkTree, yellowTree, yellowTreeBlooming, infernalMegaBloom,

        /*Ores / Overlays */
        oreIron, oreIronWall, oreCobalt, oreOxidizedCopper, oreOxidizedLead, oreQuartz,

        /*Floors*/
        redSand, lumaGrass, cinderBloomGrass, cinderBloomy, cinderBloomer, cinderBloomiest, mossyStone, mossierStone, mossiestStone, frozenGrass,
        yellowGrass, pinkGrass, mossStone,

        /*Liquid floors*/
        redSandWater, lumaGrassWater, brimstoneSlag, mossyWater, pinkGrassWater, yellowMossyWater,

        /*walls*/
        redDune, greenShrubsIrregular, greenShrubsCrooked, mossyStoneWall, mossierStoneWall, mossiestStoneWall, mossStoneWall, yellowShrubsIrregular, yellowShrubsCrooked, yellowShrubs,
        pinkShrubs, lightWall,

        /*props*/
        yellowBush, lumaFlora, bush, mossyBoulder, infernalBloom,

        //Buildings, sorted by category
        corroder, dissolver, shredder, hive, escalation, blitz, shatter,

        steamDrill, hydroElectricDrill, oilSeparator, rustyDrill,

        rustyIronConveyor, ironConveyor, cobaltConveyor, ironRouter, ironDistributor ,ironJunction, ironBridge, ironOverflow, ironUnderflow, ironUnloader,

        leadPipe, ironPipe, pipeRouter, pipeJunction, pipeBridge, displacementPump, massDisplacementPump, ironPump, rustyPump, fortifiedTank, fortifiedCanister,

        wire, wireBridge, superConductors, windMills, hydroMill, hydroElectricGenerator,

        rustyWall, rustyWallLarge, rustyWallHuge, rustyWallGigantic, ironWall, ironWallLarge, rustyScrapWall, rustyScrapWallLarge, rustyScrapWallHuge, rustyScrapWallGigantic,

        garden, bioMatterPress, unitReplicator, unitReplicatorSmall, rustElectrolyzer, steamBoiler, steamAgitator, hydrochloricGraphitePress, ironSieve, siliconArcSmelter,

        construct,

        coreRemnant, coreVestige, coreRelic, coreShrine, coreTemple, fortifiedVault, fortifiedContainer,
        mendFieldProjector, taurus,

        fortifiedMessageBlock
    ; //endregion

    public static Color nyfalisBlockOutlineColour = Color.valueOf("371404");
    public static ObjectSet<Block> nyfalisBuildBlockSet = new ObjectSet<>(), sandBoxBlocks = new ObjectSet<>(), nyfalisCores = new ObjectSet<>();

    public static void LoadWorldTiles(){
        //region World Tiles
        oreIron = new OreBlock("ore-iron", rustyIron){{
            placeableLiquid = true;
        }};
        oreIronWall = new OreBlock("ore-iron-wall", rustyIron){{
            wallOre = true;
        }};
        oreCobalt = new OreBlock("ore-cobalt", cobalt);

        /*uses ore's item as a name block in editor*/
        oreOxidizedCopper = new OreBlock("ore-oxidized-copper", copper);
        oreOxidizedLead = new OreBlock("ore-oxidized-lead", lead);

        oreQuartz = new OreBlock("ore-quartz", quartz){{
          variants = 3;
        }};

        frozenGrass = new Floor("frozen-grass"){{
            attributes.set(Attribute.water, 0.15f);
            attributes.set(bio, 0.08f);
            wall = Blocks.shrubs;
        }};

        nyfalisTree = new TreeBlock("olupis-tree");
        mossTree = new TreeBlock("moss-tree");
        yellowTree = new TreeBlock("yellow-tree");
        yellowTreeBlooming = new TreeBlock("yellow-tree-blooming");
        pinkTree = new TreeBlock("pink-tree");
        infernalMegaBloom = new TreeBlock("infernal-megabloom"){{
            variants = 4;
            clipSize = 128f;
        }};

        bush = new Prop("bush"){{
            variants = 2;
            breakSound = Sounds.plantBreak;
            mindustry.content.Blocks.grass.asFloor().decoration = this;
        }};

        yellowBush = new Prop("yellow-bush"){{
            variants = 2;
            frozenGrass.asFloor().decoration = this;
        }};

        mossyBoulder = new Prop("mossy-boulder"){{
            variants = 2;
            frozenGrass.asFloor().decoration = this;
        }};

        lumaFlora = new Prop("luma-flora"){{
            variants = 2;
            breakSound = Sounds.plantBreak;
        }};

        infernalBloom = new Prop("infernal-bloom"){{
            variants = 3;
            breakSound = Sounds.plantBreak;
        }};


        redSand = new Floor("red-sand-floor"){{
            playerUnmineable = true;
            itemDrop = Items.sand;
            decoration = mindustry.content.Blocks.redStoneBoulder;
            attributes.set(Attribute.oil, 1.5f);
        }};

        yellowGrass = new Floor("yellow-grass"){{
            variants = 4;
            decoration = yellowBush;
            attributes.set(Attribute.water, 0.15f);
            attributes.set(bio, 0.08f);
        }};

        pinkGrass = new Floor("pink-grass"){{
            variants = 4;
            decoration = mossyBoulder;
            attributes.set(Attribute.water, 0.15f);
            attributes.set(bio, 0.08f);
        }};

        lumaGrass = new Floor("luma-grass"){{
            variants = 3;
            decoration = lumaFlora;
            attributes.set(Attribute.water, 0.15f);
            attributes.set(bio, 0.08f);
        }};

        cinderBloomGrass = new Floor("cinder-bloom"){{
            variants = 3;
            decoration = infernalBloom;
            attributes.set(Attribute.water, 0.25f);
            attributes.set(bio, 0.06f);
        }};

        cinderBloomiest = new Floor("cinder-bloomiest"){{
            variants = 3;
            decoration = infernalBloom;
            attributes.set(bio, 0.01f);
        }};

        cinderBloomer = new Floor("cinder-bloomier"){{
            variants = 3;
            decoration = infernalBloom;
            attributes.set(Attribute.water, -0.05f);
            attributes.set(bio, 0.02f);
        }};

        cinderBloomy = new Floor("cinder-bloomy"){{
            variants = 3;
            decoration = Blocks.basaltBoulder;
            attributes.set(Attribute.water, -0.15f);
            attributes.set(bio, 0.03f);
        }};

        redDune = new StaticWall("red-dune-wall"){{
            attributes.set(Attribute.sand, 2f);
            redSand.asFloor().wall = this;
        }};


        redSandWater = new Floor("red-sand-water"){{
            isLiquid = supportsOverlay = true;
            liquidDrop = Liquids.water;
            status = StatusEffects.wet;
            statusDuration = 50f;
            speedMultiplier = 0.8f;
            cacheLayer = CacheLayer.water;
            variants = 0;
            albedo = 0.9f;
        }};

        pinkGrassWater = new Floor("pink-grass-water"){{
            isLiquid = supportsOverlay = true;
            liquidDrop = Liquids.water;
            status = StatusEffects.wet;
            statusDuration = 50f;
            speedMultiplier = 0.8f;
            cacheLayer = CacheLayer.water;
            variants = 0;
            albedo = 0.9f;
        }};

        brimstoneSlag = new Floor("brimstone-slag"){{
            isLiquid = emitLight = true;
            liquidDrop = Liquids.slag;
            status = StatusEffects.melting;
            statusDuration = 240f;
            speedMultiplier = 0.19f;
            damageTaken = 9999999f;
            drownTime = 30f;
            variants = 0;
            cacheLayer = CacheLayer.slag;
            lightRadius = 40f;
            lightColor = Color.valueOf("D54B3B").a(0.38f);
            attributes.set(Attribute.heat, 0.90f);
        }};

        yellowShrubs = new StaticWall("yellow-shrubs");
        pinkShrubs = new StaticWall("pink-shrubs");

        greenShrubsIrregular = new TallBlock("green-shrubs-irregular"){{
            variants = 2;
            clipSize = 128f;
        }};

        /*Irregular varrients that don't show up on top of tress*/
        greenShrubsCrooked = new StaticTree("green-shrubs-crooked"){{
            variants = 2;
            clipSize = 128f;
        }};

        yellowShrubsIrregular = new TallBlock("yellow-shrubs-irregular"){{
            variants = 2;
            clipSize = 128f;
        }};

        yellowShrubsCrooked = new TallBlock("yellow-shrubs-crooked"){{
            variants = 2;
            clipSize = 128f;
        }};

        mossyStone = new Floor("mossy-stone"){{
            decoration = Blocks.boulder;
            attributes.set(Attribute.water, 0.1f);
            attributes.set(bio, 0.1f);
        }};

        mossierStone = new Floor("mossier-stone"){{
            decoration = mossyBoulder;
            attributes.set(Attribute.water, 0.1f);
            attributes.set(bio, 0.1f);
        }};

        mossiestStone = new Floor("mossiest-stone"){{
            decoration = mossyBoulder;
            mapColor = mossierStone.mapColor;
            attributes.set(Attribute.water, 0.1f);
            attributes.set(bio, 0.1f);
        }};

        mossStone = new Floor("moss-stone"){{
            decoration = bush;
            attributes.set(Attribute.water, 0.1f);
            attributes.set(bio, 0.1f);
        }};

        mossyStoneWall = new StaticWall("mossy-stone-wall"){{
            mossierStone.asFloor().wall = this;
            attributes.set(Attribute.sand, 1f);
        }};

        mossierStoneWall = new StaticWall("mossier-stone-wall"){{
            mossierStone.asFloor().wall = this;
            attributes.set(Attribute.sand, 0.8f);
        }};

        mossiestStoneWall = new StaticWall("mossiest-stone-wall"){{
            mossiestStone.asFloor().wall = this;
            attributes.set(Attribute.sand, 0.6f);
        }};

        mossStoneWall = new StaticWall("moss-stone-wall"){{
            mossStone.asFloor().wall = this;
            attributes.set(Attribute.sand, 0.6f);
        }};

        mossyWater = new Floor("mossy-water"){{
            isLiquid = supportsOverlay = true;
            liquidDrop = Liquids.water;
            status = StatusEffects.wet;
            statusDuration = 50f;
            speedMultiplier = 0.8f;
            cacheLayer = CacheLayer.water;
            variants = 0;
            albedo = 0.9f;
        }};

        yellowMossyWater = new Floor("yellow-mossy-water"){{
            isLiquid = supportsOverlay = true;
            liquidDrop = Liquids.water;
            status = StatusEffects.wet;
            statusDuration = 50f;
            speedMultiplier = 0.8f;
            cacheLayer = CacheLayer.water;
            variants = 0;
            albedo = 0.9f;
        }};

        lumaGrassWater = new Floor("luma-grass-water"){{
            isLiquid = supportsOverlay = true;
            liquidDrop = Liquids.water;
            status = StatusEffects.wet;
            statusDuration = 50f;
            speedMultiplier = 0.8f;
            cacheLayer = CacheLayer.water;
            variants = 0;
            albedo = 0.9f;
        }};

        //endregion
    }
    public static void LoadBlocks(){
        //region Distribution
        rustyIronConveyor = new Conveyor("rusty-iron-conveyor"){{
            health = 45;
            speed = 0.015f;
            displayedSpeed = 1f;
            buildCostMultiplier = 2f;
            researchCost = with(rustyIron, 25);
            requirements(Category.distribution, with(rustyIron, 1));
        }};

        ironConveyor = new PowerConveyor("iron-conveyor"){{
            hasPower = conductivePower = consumesPower = noUpdateDisabled = true;
            health = 70;
            speed = 0.03f;
            displayedSpeed = 2f;
            unpoweredSpeed = 0.015f;
            poweredSpeed = 0.03f;
            itemCapacity = 1;
            buildCostMultiplier = 2f;

            researchCost = with(iron, 50);
            consumePower (1f/60);
            requirements(Category.distribution, with(iron, 1 ));
        }};

        cobaltConveyor = new PowerConveyor("cobalt-conveyor"){{
            hasPower = conductivePower = consumesPower = true;

            health = 70;
            speed = 0.06f;
            displayedSpeed = 4f;
            unpoweredSpeed = 0.03f;
            poweredSpeed = 0.06f;
            itemCapacity = 1;
            buildCostMultiplier = 2f;

            researchCost = with(iron, 50);
            consumePower (1f/60);
            requirements(Category.distribution, with(cobalt, 1, lead, 5 ));
        }};

        ironRouter = new Router("iron-router"){{
            buildCostMultiplier = 4f;

            researchCost = with(rustyIron, 40);
            requirements(Category.distribution, with(rustyIron, 3, lead, 1));
        }};

        ironDistributor = new Router("iron-distributor"){{
            buildCostMultiplier = 4f;
            size = 2;
            researchCost = with(rustyIron, 40);
            requirements(Category.distribution, with(rustyIron, 16, lead, 4));
        }};

        ironJunction = new Junction("iron-junction"){{
            speed = 26;
            capacity = 6;
            health = 50;
            armor = 1f;
            buildCostMultiplier = 2f;

            ((PowerConveyor)ironConveyor).junctionReplacement = this;
            ((Conveyor)rustyIronConveyor).junctionReplacement = this;
            requirements(Category.distribution, with(lead, 20, rustyIron, 30));
        }};

        ironBridge = new BufferedItemBridge("iron-bridge"){{
            fadeIn = moveArrows = false;

            health = 50;
            speed = 74f;
            range = 4;
            arrowSpacing = 6f;
            bufferCapacity = 14;
            armor = 1f;

            ((PowerConveyor)ironConveyor).bridgeReplacement = this;
            ((Conveyor)rustyIronConveyor).bridgeReplacement = this;
            requirements(Category.distribution, with(iron, 10, rustyIron, 2));
        }};

        ironOverflow = new OverflowGate("iron-overflow"){{
            buildCostMultiplier = 3f;
            requirements(Category.distribution, with(iron, 2, lead, 5));
        }};

        ironUnderflow = new OverflowGate("iron-underflow"){{
            invert = true;
            buildCostMultiplier = 3f;
            requirements(Category.distribution, with(iron, 2, lead, 5));
        }};

        ironUnloader = new DirectionalUnloader("iron-unloader"){{
            requirements(Category.distribution, with(iron, 20, graphite, 20, lead, 35));
            health = 120;
            speed = 2f;
            solid = false;
            regionRotated1 = 1;
            allowCoreUnload = true;
        }};

        //endregion
        //region Drills / crafting
        rustyDrill = new BoostableBurstDrill("rusty-drill"){{
            hasPower = true;
            squareSprite = false;
            size = 3;
            drillTime = 60f * 14f;
            tier = 1;

            drillEffect = new MultiEffect(Fx.mineImpact, Fx.drillSteam, Fx.mineImpactWave.wrap(Pal.redLight, 40f));
            researchCost = with(rustyIron,50);
            consumePower(10f/60f);
            consumeLiquid(Liquids.water, 0.02f).boost(); //TODO: Make it consume either steam or water
            requirements(Category.production, with(rustyIron, 25));
        }};


        steamDrill = new Drill("steam-drill"){{
            //requirements(Category.production, with(NyfalisItemsLiquid.iron, 25, NyfalisItemsLiquid.rustyIron, 40));
            hasPower = true;
            tier = 2;
            drillTime = 600;
            size = 3;

            envEnabled ^= Env.space;
            researchCost = with(rustyIron, 100);
            consumePower(1f/60f);
            consumeLiquid(steam, 0.05f);
            consumeLiquid(Liquids.slag, 0.06f).boost();
            requirements(Category.production, with( iron, 40));
        }};

        hydroElectricDrill = new Drill("hydro-electric-drill"){{
            tier = 3;
            drillTime = 600;
            size = 4;

            envEnabled ^= Env.space;
            consumeLiquid(steam, 0.05f);
            consumePower(0.3f);
            consumeLiquid(Liquids.slag, 0.06f).boost();
            requirements(Category.production, with(iron, 55, rustyIron, 70, lead, 30));
        }};

        garden = new AttributeCrafter("garden"){{
            hasLiquids = hasPower = hasItems = legacyReadWarmup = true;
            craftTime = 200;
            size = 3;
            maxBoost = 2.5f;

            attribute = bio;
            craftEffect = Fx.none;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawRegion("-middle"),
                    new DrawLiquidTile(Liquids.water){{alpha = 0.5f;}},
                    new DrawDefault(),
                    new DrawRegion("-top")
            );
            outputItem = new ItemStack(condensedBiomatter, 1);
            consumePower(80f / 60f);
            consumeLiquid(Liquids.water, 18f / 60f);
            requirements(Category.production, ItemStack.with(iron, 150, lead, 60, rustyIron, 30));
        }};

        //endregion
        //region liquid
        leadPipe = new Conduit("lead-pipe"){{
            leaks = underBullets = true;
            health = 60;
            liquidPressure = 0.95f;
            liquidCapacity = 5f;
            botColor = Color.valueOf("37323C");
            researchCostMultiplier = 0.5f;
            requirements(Category.liquid, with(lead, 5));
        }};

        ironPipe = new ArmoredConduit("iron-pipe"){{
            leaks = underBullets = true;
            liquidCapacity = 20f;
            botColor = Color.valueOf("252731");
            researchCostMultiplier = 3;
            requirements(Category.liquid, with(iron, 5, rustyIron, 5));
        }};

        rustyPump = new Pump("rusty-pump"){{
            pumpAmount = 0.05f;
            liquidCapacity = 10f;
            size = 1;
            requirements(Category.liquid, with(rustyIron, 5, lead, 5));
        }};

        ironPump = new Pump("iron-pump"){{
            pumpAmount = 0.1f;
            liquidCapacity = 20f;
            size = 2;
            requirements(Category.liquid, with(iron, 10));
        }};

        displacementPump = new BurstPump("displacement-pump"){{
            pumpAmount = 120f;
            leakAmount = 0.02f;
            liquidCapacity = 150f;
            size = 3;
            consumePower(0.3f);
            requirements(Category.liquid, with(iron, 20));
        }};

        massDisplacementPump = new BurstPump("mass-displacement-pump"){{
            pumpAmount = 180f;
            leakAmount = 0.1f;
            liquidCapacity = 200f;
            size = 4;
            consumePower(0.6f);
            requirements(Category.liquid, with(iron, 30));
        }};

        pipeRouter = new LiquidRouter("pipe-router"){{
            solid = underBullets = true;
            liquidCapacity = 20f;
            liquidPressure = 0.85f; /* Nerfed so you can't bypass lead pipe being terrible */
            researchCost = with(lead,50);
            requirements(Category.liquid, with(lead, 10));
        }};

        fortifiedCanister = new LiquidRouter("pipe-canister"){{
            solid = true;
            size = 2;
            liquidCapacity = 800f;
            liquidPressure = 0.95f;
            requirements(Category.liquid, with(lead, 50, iron, 20));
        }};

        fortifiedTank = new LiquidRouter("pipe-tank"){{
            solid = true;
            size = 3;
            liquidCapacity = 2300f;
            requirements(Category.liquid, with(lead, 150, iron, 60));
        }};

        pipeJunction = new LiquidJunction("pipe-junction"){{
            solid = false;
            ((Conduit)ironPipe).junctionReplacement = this;
            ((Conduit)leadPipe).junctionReplacement = this;
            researchCost = with(lead,100, rustyIron,50);

            /*expensive, since you can cheese the terribleness of pipes with this*/
            requirements(Category.liquid, with(rustyIron, 50, lead, 50));
        }};

        pipeBridge = new LiquidBridge("pipe-bridge"){{
            fadeIn = moveArrows = hasPower = false;
            arrowSpacing = 6f;
            range = 4;
            ((Conduit)ironPipe).bridgeReplacement = this;
            ((Conduit)leadPipe).bridgeReplacement = this;
            requirements(Category.liquid, with(iron, 10, lead, 20));
        }};

        oilSeparator = new GenericCrafter("oil-separator"){{
            rotate = invertFlip = true;
            size = 4;
            craftTime = 15f;
            liquidCapacity = 60f;
            liquidOutputDirections = new int[]{1,3};
            regionRotated1 = 3;

            outputLiquids = LiquidStack.with(lightOil, 4/20, heavyOil,  7/60);
            consumePower(1.2f);
            consumeLiquid(oil, 40/60f);
            researchCostMultiplier = 1.3f;
            requirements(Category.liquid, with(iron, 50));
        }};

        steamBoiler = new AttributeCrafter("steam-boiler"){{
            hasPower = hasLiquids = outputsLiquid = solid = true;
            rotate = false;
            size = 2;
            craftTime = 150f;
            liquidCapacity = 30f;
            envEnabled = Env.any;
            boostScale = 0.1f;
            attribute = Attribute.heat;

            outputLiquid = new LiquidStack(steam, 12/60f);
            consumePower(1f);
            consumeLiquid(Liquids.water, 20/60f);
            requirements(Category.liquid, with(rustyIron, 20));
        }};

        steamAgitator = new AttributeCrafter("steam-agitator"){{
            outputsLiquid = solid = true;
            displayEfficiency = rotate = false;
            size = 3;
            minEfficiency = 9f - 0.0001f;
            baseEfficiency = 0f;
            craftTime = 150f;
            liquidCapacity = 30f;
            envEnabled = Env.any;
            attribute = Attribute.steam;
            boostScale = 0.1f;

            outputLiquid = new LiquidStack(steam, 10/60f);
            requirements(Category.liquid, with(rustyIron, 30, lead, 10));
        }};

        //endregion
        //region Production
        rustElectrolyzer = new GenericCrafter("rust-electrolyzer"){{
            hasPower = hasItems = hasLiquids = solid = outputsLiquid = true;
            rotate = false;
            size = 2;
            envEnabled = Env.any;
            liquidCapacity = 24f;
            craftTime = 120;
            lightLiquid = Liquids.cryofluid;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawLiquidTile(Liquids.cryofluid){{drawLiquidLight = true;}}, new DrawDefault());

            outputItem = new ItemStack(iron, 1);
            consumePower(1f);
            consumeItems(with(lead, 1, rustyIron,1));
            consumeLiquid(Liquids.water, 12f / 60f);
            researchCost = with(rustyIron, 160, lead, 60);
            requirements(Category.crafting, with(rustyIron, 80));
        }};

        hydrochloricGraphitePress  = new GenericCrafter("hydro-graphite-press"){{
            hasItems = hasLiquids = hasPower = true;
            craftTime = 30f;
            itemCapacity = 20;
            size = 3;
            craftEffect = Fx.pulverizeMedium;

            outputItem = new ItemStack(Items.graphite, 1);
            consumeLiquids(LiquidStack.with(Liquids.oil, 5f / 60f, NyfalisItemsLiquid.steam, 10f/60f));
            consumePower(30f/60f);
            requirements(Category.crafting, with(iron, 150, rustyIron, 300));
        }};

        siliconArcSmelter = new GenericCrafter("silicon-arc-smelters") {{
            hasPower= hasItems = true;
            craftTime = 30f;
            itemCapacity = 20;
            size = 4;

            outputItem = new ItemStack(silicon, 1);
            consumeItems(with(quartz, 2, graphite, 1));
            consumePower(30f/60f);
            requirements(Category.crafting, with(lead, 100, graphite, 60, iron, 30));
        }};

        bioMatterPress = new GenericCrafter("biomatter-press"){{
            hasLiquids = hasPower = true;
            craftTime = 20f;
            liquidCapacity = 60f;
            size = 2;
            health = 320;

            craftEffect = Fx.none;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawPistons(){{
                        sinMag = 1f;
                    }},
                    new DrawDefault(),
                    new DrawLiquidRegion(),
                    new DrawRegion("-top")
            );
            outputLiquid = new LiquidStack(oil, 18f / 60f);
            consumePower(0.7f);
            consumeItem(condensedBiomatter, 1);
            requirements(Category.crafting, with(iron, 100, lead, 30));
        }};

        ironSieve  = new GenericCrafter("iron-sieve"){{
            //not to be confused with iron shiv
            hasPower = hasItems = true;
            hasLiquids = false;
            craftTime = 30f;
            itemCapacity = 20;
            size = 2;

            craftEffect = Fx.pulverizeMedium;
            outputItem = new ItemStack(rustyIron, 2);
            consumePower(1.8f);
            consumeItem(Items.sand, 2);
            requirements(Category.crafting, with(rustyIron, 60));
        }};

        //endregion
        //region Units
        construct = new PowerUnitTurret("construct"){{
            requirements(Category.units, with(iron, 200, lead, 100, silicon, 60));
            shootType = new BasicBulletType(2.5f, -1){{
                shootEffect = Fx.unitLand;
                ammoMultiplier = 1f;
                spawnUnit = spirit;
            }};

            displayUnits = Seq.with(spirit);
            consumePower(6f);
            alwaysShooting = true;
            shootSound = Sounds.respawn;
            shootY = 0f;
            size = 4;
            reload = 600f;
        }};

        unitReplicator = new Replicator("unit-replicator"){{
            size = 5;
            delay = 5;

            this.requirements(Category.units, BuildVisibility.sandboxOnly, ItemStack.with());
        }};

        unitReplicatorSmall = new Replicator("unit-replicator-small"){{
            size = 4;
            delay = 4;

            this.requirements(Category.units, BuildVisibility.sandboxOnly, ItemStack.with());
        }};

        //endregion
        //region Turrets
        corroder = new LiquidTurret("corroder"){{ //architronito
            requirements(Category.turret, with(rustyIron, 50, lead, 10));
            ammo(
                Liquids.water, new LiquidBulletType(Liquids.water){{
                        status = StatusEffects.corroded;
                        layer = Layer.bullet -2f;

                        lifetime = 19.5f;
                        speed = 5.5f;
                        drag = 0.008f;
                        damage = 15;
                        statusDuration = 60f * 2;
                        ammoMultiplier = 5f;
                        rangeChange = 15f;

                }},
                steam, new NoBoilLiquidBulletType(steam){{
                        evaporatePuddles = pierce = true;
                        status = StatusEffects.corroded;

                        lifetime = 12f;
                        speed = 8f;
                        damage = 18f;
                        drag = 0.009f;
                        ammoMultiplier = 3f;
                        statusDuration = 60f * 5;
                        pierceCap = 1;
                    }}
            );
            drawer = new DrawTurret("iron-"){{
                parts.addAll(
                        new RegionPart("-barrel"){{
                            layerOffset = -0.1f;
                            progress = PartProgress.recoil;
                            moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
                            y = 1f;
                            mirror = false;
                        }},  new RegionPart("-front-wing"){{
                            layerOffset = -0.1f;
                            progress = PartProgress.warmup;
                            moves.add(new PartMove(PartProgress.recoil, 0f, 0, -12f));
                            mirror = true;
                        }}, new RegionPart("-back-wing"){{
                            layerOffset = -0.1f;
                            progress = PartProgress.smoothReload;
                            moves.add(new PartMove(PartProgress.recoil, 0f, -1f, 12f));
                            mirror = true;
                        }}
                );
            }};

            targetAir = true;
            liquidCapacity = 5f;
            recoil = 1;
            shootY = reload = 10f;
            range = 90f;
            shootCone = 50f;
            health = 1500;
            size = 2;
            outlineColor = nyfalisBlockOutlineColour;

            researchCost = with(rustyIron, 100);
            consumePower(1f);
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
            loopSound = Sounds.steam;

        }};

        dissolver = new LiquidTurret("dissolver"){{ //architonnerre
            requirements(Category.turret, with(iron, 50, lead, 50));
            ammo(
                    Liquids.water, new LiquidBulletType(Liquids.water){{
                        lifetime = 28.5f;
                        speed = 5.8f;
                        hitSize = 7f;
                        damage = 24;
                        drag = 0.0009f;
                        ammoMultiplier = 4f;
                        statusDuration = 60f * 2;
                        rangeChange = 40f;
                        size = 3;

                        status = StatusEffects.corroded;
                        layer = Layer.bullet -2f;
                        puddleSize = 7f;
                    }},
                    steam, new NoBoilLiquidBulletType(steam){{
                        collidesAir = pierce = evaporatePuddles = true;

                        lifetime = 14.5f;
                        speed = 8.8f;
                        hitSize = 7f;
                        damage = 22f;
                        drag = 0.0009f;
                        ammoMultiplier = 3f;
                        statusDuration = 60f * 4;
                        status = StatusEffects.corroded;
                    }}
            );
            targetAir = true;
            recoil = 0.2f;
            reload = 5f;
            range = 130f;
            shootCone = 50f;
            health = 2500;
            size = 3;

            outlineColor = nyfalisBlockOutlineColour;
            drawer = new DrawTurret("iron-");

            consumePower(1.5f);
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
            loopSound = Sounds.steam;
        }};

        shredder = new ItemTurret("shredder"){{
            requirements(Category.turret, with(iron, 100, lead, 20, graphite, 20));
            ammo(
                    //TODO: Some how ignore Allied Non-Solids??? (ex: mines & conveyors)
                    rustyIron, new BasicBulletType(2.5f, 11){{
                        collidesTeam = true;
                        collideTerrain = collidesAir = false;
                        status = StatusEffects.slow;
                        statusDuration = 60f * 2f;
                        width = 40f;
                        height = 9f;
                        lifetime = 60f;
                        ammoMultiplier = pierceCap = 2;
                        knockback= 3f;
                        frontColor = backColor = Color.valueOf("ea8878");
                    }},
                    iron, new BasicBulletType(3f, 23){{
                            collidesTeam = collideTerrain = true;
                            collidesAir = false;
                            status = StatusEffects.slow;
                            statusDuration = 60f * 3f;
                            width = 40f;
                            height = 11f;
                            lifetime = 50f;
                            ammoMultiplier = 2;
                            pierceCap = 3;
                            knockback = 3f;
                            frontColor = backColor = Color.valueOf("ea8878");
                }}
            );

            targetAir = false;
            size = 3;
            health = 350;
            armor = 5;
            rotateSpeed = 10f;
            reload = 60f;
            range = 160;
            shootCone = 15f;
            shoot = new ShootSummon(0f, 0f, 0f, 0f);
            shootY = Vars.tilesize * size;
            ammoUseEffect = Fx.casing1;
            outlineColor = nyfalisBlockOutlineColour;
            coolant = consumeCoolant(0.1f);
            limitRange(1f);
            researchCostMultiplier = 0.05f;
        }};

        hive = new ItemUnitTurret("hive"){{
            requirements(Category.turret, with(iron, 100, lead, 30, silicon, 30));
            ammo(
                silicon, new BasicBulletType(2.5f, 11){{
                    shootEffect = Fx.shootBig;
                    ammoMultiplier = 1f;
                    spawnUnit = mite;
                }}
            );

            displayUnits = Seq.with(mite);
            shootSound = Sounds.respawn;
            shootY = 0f;
            size = 4;
            reload = 600f;
            range = 650;
        }};

        //TODO: Escalation - A early game rocket launcher that acts similarly to the scathe but with lower range and damage. (Decent rate of fire, weak against high health single targets, slow moving rocket, high cost but great AOE)
        //TODO: Blitz (Recursor) - A recursive mortar turret that shoots long ranged recursive shells at the enemy (Has Really low rate of fire, high range, shells explode into multiple more shells on impact)
        //TODO:Shatter - A weak turret that shoots a spray of glass shards at the enemy. (High rate of fire, low damage, has pierce, very low defense, low range)

        //endregion
        //region Power
        wire = new Wire("wire"){{
            floating = placeableLiquid = consumesPower = outputsPower = true;
            solid = false;
            baseExplosiveness = 0.5f;
            researchCost = with(rustyIron,20);
            consumePowerBuffered(1f);
            consumePower(1f/60f);
            requirements(Category.power, with(rustyIron, 5));
        }};

        superConductors = new Wire("super-conductor"){{
            floating = true;
            solid = false;
            baseExplosiveness = 0.7f;
            health = 150;
            requirements(Category.power, with(cobalt, 20, iron, 10));
        }};

        wireBridge = new BeamNode("wire-bridge"){{
            consumesPower = outputsPower = floating = true;
            baseExplosiveness = 0.6f;
            range = 5;
            health = 100;
            pulseMag = 0f;
            laserWidth = 0.4f;
            laserColor1 = Color.valueOf("ACB5BA");
            laserColor2 = Color.valueOf("65717E");
            consumePower(10f/ 60f);
            requirements(Category.power, with(iron, 30, Items.lead, 15));
        }};

        windMills = new WindMill("wind-mill"){{
            size = 3;
            powerProduction = 10f/60f;
            displayEfficiencyScale = 1.1f;
            attribute = Attribute.steam;
            drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 0.6f * 9f){{
                blurThresh =  0.01f;
            }});
            researchCost = with(rustyIron, 75);
            requirements(Category.power, with(rustyIron, 30));
        }};

        hydroMill = new ThermalGeneratorNoLight("hydro-mill"){{
            requirements(Category.power, with(iron, 30, rustyIron, 50));
            floating = true;
            powerProduction = 17f/60f;
            size = 3;
            attribute = hydro;
            generateEffect = Fx.steam;
            effectChance = 0.011f;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 0.6f * 9f){{
                blurThresh = 0.01f;
            }});
        }};

        hydroElectricGenerator = new ThermalGeneratorNoLight("hydro-electric-generator"){{
            placeableLiquid = floating = true;
            powerProduction = 23f/60f;
            size = 5;
            attribute = hydro;
            generateEffect = Fx.steam;
            effectChance = 0.011f;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 0.6f * 9f){{
                blurThresh = 0.01f;
            }});
            requirements(Category.power, with(iron, 30, rustyIron, 50));
        }};

        //TODO: Solar receiver & Mirror -> Super structure `Mirror(s)->Redirector->Solar tower+water=steam->steam turbine(s)`

        //endregion
        //region Wall
        rustyWall = new Wall("rusty-wall"){{
            size = 1;
            health =  350;
            researchCost = with(rustyIron,80);
            requirements(Category.defense,with(rustyIron, 12));
        }};

        rustyWallLarge = new Wall("rusty-wall-large"){{
            size = 2;
            health =  1400;
            researchCost = with(rustyIron,200);
            requirements(Category.defense,with(rustyIron, 48));
        }};

        rustyWallHuge = new Wall("rusty-wall-huge"){{
            size = 3;
            health = 2690;
            researchCost = with(rustyIron,1000);
            requirements(Category.defense,with(rustyIron, 620));
        }};

        rustyWallGigantic = new Wall("rusty-wall-gigantic"){{
            size = 4;
            health = 3600;
            researchCost = with(rustyIron,4200);
            requirements(Category.defense, BuildVisibility.editorOnly, with(rustyIron, 1500));
        }};

        ironWall = new Wall("iron-wall"){{
            size = 1;
            health = 700;
            requirements(Category.defense,with(iron, 12));
        }};

        ironWallLarge = new Wall("iron-wall-large"){{
            size = 2;
            health = 2800;
            requirements(Category.defense,with(iron, 196));
        }};

        rustyScrapWall = new Wall("rusty-scrap-wall"){{
            size = 1;
            health = 240;
            variants = 1;
            requirements(Category.defense, BuildVisibility.editorOnly, with(rustyIron, 6, scrap, 3));
        }};

        rustyScrapWallLarge = new Wall("rusty-scrap-wall-large"){{
            health = 960;
            size = 2;
            variants = 3;
            requirements(Category.defense, BuildVisibility.editorOnly, ItemStack.mult(rustyScrapWall.requirements, 4));
        }};

        rustyScrapWallHuge = new Wall("rusty-scrap-wall-huge"){{
            size = 3;
            health = 3840;
            variants  = 2;
            requirements(Category.defense, BuildVisibility.editorOnly, ItemStack.mult(rustyScrapWall.requirements, 9));
        }};

        rustyScrapWallGigantic = new Wall("rusty-scrap-wall-gigantic"){{
            size = 4;
            health = 2530;
            requirements(Category.defense, BuildVisibility.editorOnly, ItemStack.mult(rustyScrapWall.requirements, 16));
        }};

        //endregion
        //region Effect
        mendFieldProjector = new DirectionalMendProjector ("mend-field-projector"){{
            size = 2;
            reload = 200f;
            range = 40f;
            healPercent = 4f;
            phaseBoost = 4f;
            phaseRangeBoost = 20f;
            health = 80;
            consumePower(0.3f);
            consumeItem(Items.silicon).boost();
            requirements(Category.effect, with(Items.lead, 30, iron, 25));
        }};

        taurus = new MendTurret("taurus"){{
            requirements(Category.effect, with(iron, 30, Items.lead, 40));
            flags = EnumSet.of(BlockFlag.repair, BlockFlag.turret);
            consumePower(3.3f);
            size = 3;
            reload = 10f;
            recoils = 2;
            shoot = new ShootAlternate(9f);
            outlineColor = nyfalisBlockOutlineColour;

            shootEffect = Fx.shootHeal;
            drawer = new DrawTurret("iron-"){{
                for(int i = 0; i < 2; i ++){
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                        progress = PartProgress.recoil;
                        recoilIndex = f;
                        under = true;
                        moveY = -1.5f;
                    }});
                }
            }};
            shootType = new BasicBulletType(5.2f, -5, "olupis-diamond-bullet"){{
                lifetime = 30f;
                healPercent = 7f;
                collidesTeam = true;
                collidesAir =  false;
                backColor = Pal.heal;
                frontColor = Color.white;
                shootSound = Sounds.sap;
                width = 10f;
                height = 16f;
                /*added slight homing so it can hit 1x1 blocks*/
                homingPower = 0.02f;
            }};
            limitRange(2);
        }};

        fortifiedContainer = new StorageBlock("fortified-container"){{
            coreMerge = false;
            size = 2;
            itemCapacity = 1400;
            scaledHealth = 150;
            health =  740;
            requirements(Category.effect, with(rustyIron, 75, iron, 50));
        }};

        fortifiedVault = new StorageBlock("fortified-vault"){{
            coreMerge = false;
            size = 3;
            itemCapacity = 2700;
            scaledHealth = 120;
            health =  1500;
            requirements(Category.effect, with(rustyIron, 150, iron, 100));
        }};

        coreRemnant = new PropellerCoreBlock("core-remnant"){{
            alwaysUnlocked = isFirstTier = true;
            size = 2;
            unitType = gnat;
            itemCapacity = 1500;
            health = 3500;

            requirements(Category.effect, with(rustyIron, 1500, lead, 1500));
        }};

        coreVestige = new PropellerCoreBlock("core-vestige"){{
            unitType = gnat;
            size = 3;
            itemCapacity = 3000;
            health = 7000;
            requirements(Category.effect, with(rustyIron, 1500, iron, 1000));
        }};

        coreRelic = new CoreBlock("core-relic"){{
            unitType = gnat;
            size = 4;
            itemCapacity = 4500;
            health = 140000;
            requirements(Category.effect, with(rustyIron, 1500, iron, 1000));
        }};

        coreShrine = new CoreBlock("core-shrine"){{
            unitType = gnat;
            size = 5;
            itemCapacity = 6000;
            health = 280000;
            requirements(Category.effect, with(rustyIron, 1500, iron, 1000));
        }};

        coreTemple = new CoreBlock("core-temple"){{
            unitType = gnat;
            size = 6;
            itemCapacity = 7500;
            health = 560000;
            requirements(Category.effect, with(rustyIron, 1500, iron, 1000));
        }};

        lightWall = new PrivilegedLightBlock("light-wall"){{
            alwaysUnlocked = true;
            brightness = 0.75f;
            radius = 140f;
            requirements(Category.effect, BuildVisibility.editorOnly, with());
        }};
        //endregion
        //region Logic
        fortifiedMessageBlock = new MessageBlock("fortified-message-block"){{
            health = 100;
            requirements(Category.logic, with(Items.graphite, 10, iron, 5));
        }};
        //endregion
    }
    public static void AddAttributes(){
        grass.attributes.set(bio, 0.1f);
        stone.attributes.set(bio, 0.03f);
        charr.attributes.set(bio, 0.03f);
        mud.attributes.set(bio, 0.03f);
        dirt.attributes.set(bio, 0.03f);
        snow.attributes.set(bio, 0.01f);
        ice.attributes.set(bio, 0.01f);
        craters.attributes.set(bio, 0.5f);

        deepwater.attributes.set(hydro, 0.5f);
        deepTaintedWater.attributes.set(hydro, 0.3f);
        Blocks.water.attributes.set(hydro, 0.3f);
        taintedWater.attributes.set(hydro, 0.3f);
        sandWater.attributes.set(hydro, 0.3f);
        darksandTaintedWater.attributes.set(hydro, 0.3f);
        darksandWater.attributes.set(hydro, 0.3f);

        redSandWater.attributes.set(hydro, 0.3f);
        lumaGrassWater.attributes.set(hydro, 0.3f);
        mossyWater.attributes.set(hydro, 0.3f);
        pinkGrassWater.attributes.set(hydro, 0.3f);
        yellowMossyWater.attributes.set(hydro, 0.3f);
    }

    public static void NyfalisBlocksPlacementFix(){
        nyfalisBuildBlockSet.clear();
        Vars.content.blocks().each(b->{
            if(b.name.startsWith("olupis-") && b.isVisible()) nyfalisBuildBlockSet.add(b);
        });

        nyfalisCores.addAll(coreRemnant, coreRelic, coreShrine, coreTemple, coreVestige);

        sandBoxBlocks.addAll(
                /*just to make it easier for testing and/or sandbox*/
                itemSource, itemVoid, liquidSource, liquidVoid, payloadSource, payloadVoid, powerSource, powerVoid,
                worldProcessor, logicProcessor, microProcessor, hyperProcessor, message, worldMessage, reinforcedMessage,
                logicDisplay, largeLogicDisplay, canvas, payloadConveyor, payloadRouter
        );
    }
}
