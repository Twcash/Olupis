package olupis.world.entities.packets;

import arc.util.io.*;
import mindustry.io.*;
import mindustry.net.*;
import mindustry.type.*;
import olupis.world.blocks.defence.ItemUnitTurret.*;

public class ConstructorCheatConfigPacket extends Packet{
    public ItemUnitTurretBuild build;
    public Item conf;

    private byte[] DATA;

    public ConstructorCheatConfigPacket() {
        this.DATA = NODATA;
    }

    public void write(Writes WRITE) {
        TypeIO.writeObject(WRITE, build);
        TypeIO.writeObject(WRITE, conf);
    }

    public void read(Reads READ, int LENGTH) {
        this.DATA = READ.b(LENGTH);
    }

    public void handled() {
        BAIS.setBytes(this.DATA);
        this.build = (ItemUnitTurretBuild)TypeIO.readObject(READ);
        this.conf = (Item)TypeIO.readObject(READ);
    }

    public void handleServer(NetConnection con) {
        build.requestCheat( conf);
    }
}
