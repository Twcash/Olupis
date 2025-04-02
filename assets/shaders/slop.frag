#define HIGHP

#define NSCALE (180.0 / 2.0)
uniform sampler2D u_texture;
uniform sampler2D u_noise;

uniform vec2 u_campos;
uniform vec2 u_resolution;
uniform float u_time;

varying vec2 v_texCoords;

void main() {
    vec2 c = v_texCoords.xy;
    
    // Corrected coordinate calculation
    vec2 coords = (c * u_resolution + u_campos) / u_resolution;

    float btime = u_time / 24000.0;
    float noise = (texture(u_noise, coords / NSCALE + vec2(btime) * vec2(-0.9, 0.8)).r + 
                   texture(u_noise, coords / NSCALE + vec2(btime * 1.1) * vec2(-0.8, -1.0)).r) / 2.0;
                   
    vec4 color = texture(u_texture, c);

    if (noise > 0.44 && noise < 0.48) {
        color.rgb *= vec3(0.3, 0.15, 0.3);
    }
    if (noise > 0.54 && noise < 0.58) {
        color.rgb *= vec3(0.2, 0.05, 0.35);
    } 
    if (noise > 0.14 && noise < 0.18) {
        color.rgb *= vec3(0.8, 0.05, 0.2);
    }

    gl_FragColor = color;
}