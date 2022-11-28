package com.example.agsl_art.ui.shader.thebookofshaders.es05

import org.intellij.lang.annotations.Language

@Language("AGSL")
const val PLOT_SHADER = """
    uniform float2 iResolution;
    uniform float iTime;
    uniform shader contents; 

    float plot(vec2 st) {    
        return smoothstep(0.02, 0.0, abs(st.y - st.x));
    }
    
    vec4 main(in vec2 fragCoord) {
        // convert between in space GLSL
        fragCoord.y = iResolution.y - fragCoord.y;

        vec2 st = fragCoord.xy/iResolution;
    
        float x = st.x;
        float y = st.y;
    
        vec3 colorX = vec3(x);
        vec3 colorY = vec3(y);
        
        vec3 color = vec3((colorX.x + colorY.x) / 2.0, (colorX.y + colorY.y) / 2.0, (colorX.z + colorY.z) / 2.0);
    
        // Plot a line
        float pct = plot(st);
        color = (1.0-pct)*color+pct*vec3(0.0,1.0,0.0);
    
        return vec4(color,1.0);
        
//        fragCoord.x += sin(fragCoord.y / 3) * 4;
//        return contents.eval(fragCoord);
    }
"""