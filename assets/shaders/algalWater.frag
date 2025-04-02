#define HIGHP

#define NSCALE 180.0 / 2.0

uniform sampler2D u_texture;
uniform sampler2D u_noise;

uniform vec2 u_campos;
uniform vec2 u_resolution;
uniform float u_time;

varying vec2 v_texCoords;

#define S1 vec3(0.4, 0.65, 0.5) / 255.0
#define S2 vec3(0.4, 0.67, 0.5) / 255.0


void main() {
    vec2 c = v_texCoords.xy;
    vec2 coords = vec2(c.x * u_resolution.x + u_campos.x, c.y * u_resolution.y + u_campos.y);

    float btime = u_time / 24000.0;
    vec2 v = vec2(1.0 / u_resolution.x, 1.0 / u_resolution.y);

    float stime = u_time / 20.0;
    float noise = (texture2D(u_noise, (coords) / NSCALE + vec2(btime) * vec2(-0.9, 0.8)).r + 
                   texture2D(u_noise, (coords) / NSCALE + vec2(btime * 1.1) * vec2(-0.8, -1.0)).r) / 2.0;

vec4 sampled = texture2D(u_texture, c + vec2(sin(stime/3.0 + coords.y/0.75) * v.x, 0.0));
    vec3 color = sampled.rgb * vec3(0.9, 0.9, 1);
    float tester = mod((coords.x + coords.y * 1.1 + sin(stime / 8.0 + coords.x / 5.0 - coords.y / 100.0) * 2.0) +
                       sin(stime / 9.0 + coords.y / 3.0) * 1.0 +
                       sin(stime / 16.0 - coords.y / 2.0) * 2.0 +
                       sin(stime / 7.0 + coords.y / 1.0) * 0.5 +
                       sin(coords.x / 6.0 + coords.y / 7.0) +
                       sin(stime / 1.0 + coords.x / 4.0) * 5.0, 80.0);

    if (tester < 8.0) {
        color *= 1.3;
    }
    if (noise < 0.65 && noise > 0.55) {
        color *= S1;
    }
        if (noise < 0.44) {
        color = *S2;
    }
    
    if (tester < 8.0 && noise < 0.6 && noise > 0.5 ||tester < 8.0 && noise < 0.44) {
        color *= 1.3;
    }
    

    float alpha = sampled.a;
    color.rgb = mix(color.rgb, vec3(0.0), 1.0 - alpha);  // Blend with black based on 	alpha
    gl_FragColor = vec4(color.rgb, alpha);}