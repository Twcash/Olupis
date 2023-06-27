package olupis.world.planets;

import arc.struct.Seq;
import mindustry.content.Liquids;
import mindustry.game.Objectives;
import olupis.content.NyfalisSectors;

import static mindustry.content.Items.*;
import static mindustry.content.TechTree.*;
import static olupis.content.NyfalisBlocks.*;
import static olupis.content.NyfalisItemsLiquid.*;
import static olupis.content.NyfalisPlanets.*;

public class NyfalisTechTree {

    public static void load(){
        nyfalis.techTree = nodeRoot("olupis", coreRemnant, () -> {

            node(NyfalisSectors.placeholder1, () -> {
                node(NyfalisSectors.placeholder2, Seq.with(
                        new Objectives.SectorComplete(NyfalisSectors.placeholder1)
                ), () ->{

                });
            });

            node(mossyBoulder, ()-> {

                node(wire, Seq.with(new Objectives.Research(rustyDrill)), () -> {
                    node(windMills, () -> {
                        node(rustElectrolyzer, () -> {
                            node(garden,()->{
                                node(bioMatterPress, () ->{

                                });
                            });
                            node(hydrochloricGraphitePress, ()->{
                                node(siliconArcSmelter, ()->{

                                });
                            });
                        });
                        node(wireBridge, ()-> {
                            node(hydroMill, ()->{
                                node(hydroElectricGenerator, () ->{

                                 });
                            });
                        });
                        node(taurus, ()->{

                        });
                    });
                });

                node(leadPipe, Seq.with(new Objectives.Research(rustyDrill)), () -> {
                    node(rustyPump, () ->{
                        node(pipeRouter, ()->{
                            node(fortifiedCanister, ()->{
                                node(fortifiedTank, ()->{

                                });
                            });
                            node(pipeJunction, ()->{
                                node(pipeBridge, ()-> {

                                });
                            });
                            node(ironPipe, ()->{

                            });
                            node(steamBoiler, ()->{
                                node(steamAgitator, ()->{

                                });
                            });
                            node(ironPump, () -> {
                                node(displacementPump, () -> {
                                    node(massDisplacementPump, () -> {

                                    });
                                });
                            });
                        });
                    });
                });

                node(rustyIronConveyor, () ->{
                    node(ironRouter, () ->{
                        node(ironJunction, ()->{
                            node(ironBridge, ()->{

                            });
                        });
                        node(ironConveyor, ()->{

                        });

                        node(ironUnloader, () ->{
                            node(fortifiedContainer, () ->{
                                node(fortifiedVault, () ->{

                                });
                            });
                        });
                    });

                    node(rustyDrill, ()->{
                        node(steamDrill, () ->{
                            node(hydroElectricDrill, () ->{

                            });
                        });
                    });
                });


                node(corroder, Seq.with(new Objectives.Research(ironRouter)), ()-> {
                    node(dissolver, ()->{

                    });
                    node(shredder, ()->{
                        node(hive, ()->{

                        });
                    });
                    node(rustyWall, () ->{
                        node(rustyWallLarge, ()->{
                            node(rustyWallHuge, ()->{

                            });
                        });
                        node(ironWall, ()->{
                            node(ironWallLarge, ()->{

                            });
                        });
                    });
                });

                node(construct, Seq.with(new Objectives.Research(ironRouter)), ()->{
                    node(unitReplicator, ()->{
                        node(unitReplicatorSmall, ()->{

                        });
                    });
                });

                node(taurus, Seq.with(new Objectives.Research(windMills)), ()->{

                });

                node(fortifiedMessageBlock, Seq.with(new Objectives.Research(ironRouter)), ()->{

                });


                nodeProduce(rustyIron, () ->{
                    nodeProduce(lead, () ->{
                        nodeProduce(sand, () ->{
                            nodeProduce(quartz, () ->{

                            });
                        });
                    });
                    nodeProduce(iron, () ->{
                        nodeProduce(condensedBiomatter, () ->{

                        });
                        nodeProduce(cobalt, ()->{

                        });
                    });
                    nodeProduce(Liquids.water, ()->{
                        nodeProduce(steam, () ->{

                        });
                        nodeProduce(Liquids.oil,()->{
                            nodeProduce(graphite, ()->{
                                nodeProduce(silicon, ()->{

                                });
                            });
                        });
                    });
                });

            });
        });
        arthin.techTree = nyfalis.techTree;
        spelta.techTree = nyfalis.techTree;
        system.techTree = nyfalis.techTree;
    }
}
