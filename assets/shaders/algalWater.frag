#define HIGHP

uniform sampler2D u_texture;
uniform sampler2D u_noise;

uniform vec2 u_campos;
uniform vec2 u_resolution;
uniform float u_time;

varying vec2 v_texCoords;

const float NSCALE = 20.0; // Define NSCALE
const float mscl = 40.0;
const float mth = 7.0;
#define btime (u_time * 0.3) // Define btime

void main() {
    vec2 c = v_texCoords;
    vec2 v = vec2(1.0 / u_resolution.x, 1.0 / u_resolution.y);
    vec2 coords = vec2(c.x / v.x + u_campos.x, c.y / v.y + u_campos.y);

    float stime = u_time / 20.0;

    // Sample texture with slight distortion
    vec4 sampled = texture2D(u_texture, c + vec2(sin(stime / 3.0 + coords.y / 0.75) * v.x, 0.0));
    vec3 color = sampled.rgb * vec3(0.9, 0.9, 1);

    float tester = mod((coords.x + coords.y * 2.1 + sin(stime / 20.0 + coords.x / 5.0 - coords.y / 50.0) * 12.0) +
                       sin(stime / 2.0 + coords.y / 3.0) * 1.0 +
                       sin(stime / 1.0 - coords.y / 2.0) * 2.0 +
                       sin(stime / 20.0 + coords.y / 1.0) * 0.5 +
                       sin(coords.x / 2.0 + coords.y / 5.0) +
                       sin(stime / 1.0 + coords.x / 4.0) * 5.0, 80.0);

    // Noise calculations
    float noise1 = texture2D(u_noise, (coords / NSCALE) + vec2(btime) * vec2(-0.9, 0.8)).r;
    float noise2 = texture2D(u_noise, (coords / NSCALE) + vec2(btime * 1.1) * vec2(-0.8, -1.0)).r;
    float noise = (noise1 + noise2) / 2.0;

    if (tester < 7.0) {
        color *= 1.4;
    }
    if (tester < 2.0) {
        color *= 1.1;
    }

    if (noise < 0.1&& noise < 0.13) {
        color *= vec3(0.4, 0.5, 0.7);
    }
    if (noise2 < 0.18&& noise < 0.2) {
        color *= vec3(0.4, 0.6, 0.5);
    }

    gl_FragColor = vec4(color, min(sampled.a * 100.0, 1.0));
}