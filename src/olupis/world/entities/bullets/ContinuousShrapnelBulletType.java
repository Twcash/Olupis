package olupis.world.entities.bullets;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;

//ContinuousLaserBulletType with ShrapnelBulletType style of rendering
public class ContinuousShrapnelBulletType extends ContinuousLaserBulletType{
    public int serrations = 7;
    public float
        serrationLenScl = 10f,
        serrationWidth = 2f,
        serrationSpacing = 8f,
        serrationSpaceOffset = 80f,
        serrationFadeOffset = 0.5f,
        serrationAngle = 0,
        serrationFirstOffset = 0,
        serrationAlphaMul = 0.95f,
        serrationLengthMul = 1f
    ;

    public ContinuousShrapnelBulletType(float damage){
        this.damage = damage;
    }

    public ContinuousShrapnelBulletType(){}


    @Override
    public void draw(Bullet b){
        float fout = Mathf.clamp(b.time > b.lifetime - fadeTime ? 1f - (b.time - (lifetime - fadeTime)) / fadeTime : 1f);
        float realLength = Damage.findLength(b, length * fout, laserAbsorb, pierceCap);
        float rot = b.rotation();

        for(int c = 0; c < colors.length; c++){
            Draw.color(Tmp.c1.set(colors[c]).mul(1f + Mathf.absin(Time.time, 1f, 0.1f)));
            float colorFin = c / (float)(colors.length - 1);
            float baseStroke = Mathf.lerp(strokeFrom, strokeTo, colorFin);
            float stroke = (width + Mathf.absin(Time.time, oscScl, oscMag)) * fout * baseStroke;
            float ellipseLenScl = Mathf.lerp(1 - c / (float)(colors.length), 1f, pointyScaling);

            Draw.alpha(serrationAlphaMul * colors[c].a);
            for(int i = 0; i < serrations; i++){
                Tmp.v1.trns(rot, i * serrationSpacing + serrationFirstOffset);
                float sl = (Mathf.clamp(fout - serrationFadeOffset) * (serrationSpaceOffset - i * serrationLenScl)) * serrationLengthMul * ellipseLenScl;
                Drawf.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, serrationWidth + Mathf.absin(Time.time, oscScl, oscMag), sl, b.rotation() + 90 + serrationAngle);
                Drawf.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, serrationWidth + Mathf.absin(Time.time, oscScl, oscMag), sl, b.rotation() - 90 - serrationAngle);
            }
            Draw.alpha(colors[c].a);
            Lines.stroke(stroke);
            Lines.lineAngle(b.x, b.y, rot, realLength - frontLength, false);

            //back ellipse
            Drawf.flameFront(b.x, b.y, divisions, rot + 180f, backLength, stroke / 2f);

            //front ellipse
            Tmp.v1.trnsExact(rot, realLength - frontLength);
            Drawf.flameFront(b.x + Tmp.v1.x, b.y + Tmp.v1.y, divisions, rot, frontLength * ellipseLenScl, stroke / 2f);

            Draw.reset();
        }

        Tmp.v1.trns(b.rotation(), realLength * 1.1f);

        Drawf.light(b.x, b.y, b.x + Tmp.v1.x, b.y + Tmp.v1.y, lightStroke, lightColor, 0.7f);
        Draw.reset();
    }

}
