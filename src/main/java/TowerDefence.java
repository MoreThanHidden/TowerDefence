import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;


public class TowerDefence {

    // The window handle
    private long window;
    Camera camera;

    public void run() {

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        //Error Callback
        GLFWErrorCallback.createPrint(System.err).set();

        //init GLFW or Error
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        // Create the window
        window = glfwCreateWindow(300, 300, "Tower Defence!", NULL, NULL);

        //Check the Window
        if ( window == NULL ) throw new RuntimeException("Failed to create the GLFW window");

        // Escape Key Callback
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWWindowSizeCallback callback = GLFWWindowSizeCallback.create(this::windowSizeCallback);
            glfwSetWindowSizeCallback(window, callback);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );

        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

    }

    private void update(){
        int size = 18;
        float UNIT = 0.1f;
        for(int y = 0; y < size; y++) {
            for (int i = 0; i < size; i ++) {
                int currentTile = Maps.DefaultMap[i + (18 * y)];
                glBegin(GL_QUADS);
                if(currentTile == 0){glColor4f(0.1f, 0.6f, 0.1f, 0.0f);}else{glColor4f(0.6f, 0.4f, 0.1f, 0.0f);}
                glVertex2f(-0.8f + UNIT * i, 0.9f - (UNIT * y));
                glVertex2f(-0.8f + UNIT * i, 0.8f - (UNIT * y));
                glVertex2f(-0.9f + UNIT * i, 0.8f - (UNIT * y));
                glVertex2f(-0.9f + UNIT * i, 0.9f - (UNIT * y));
                glEnd();
            }
        }

    }

    private void loop() {
        GL.createCapabilities();
        // Set the clear color
        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer


            update();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    private void windowSizeCallback(long window, int width, int height){
            //camera.setProjection(width, height);
            glViewport(0, 0, width, height);
    }

}
