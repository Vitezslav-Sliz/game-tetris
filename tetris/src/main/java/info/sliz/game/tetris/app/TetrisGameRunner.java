package info.sliz.game.tetris.app;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.translate.ITranslator;
import info.sliz.game.tetris.translate.Translate.KEY;
import info.sliz.game.tetris.translate.impl.Translator;
import info.sliz.game.tetris.ui.elements.basic.FxCube;
import info.sliz.game.tetris.ui.elements.basic.FxPlayCube;
import info.sliz.game.tetris.ui.gamespace.FxGameSpace;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TetrisGameRunner extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(TetrisGameRunner.class);
    private final ITranslator trans = Translator.getInstance();
    private final Group root = new Group();
    private final Group world = new Group();
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private FxCube cube = new FxPlayCube(10);
    
    private void buildScene() {

        root.getChildren().add(world);
        
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-200);
        root.getChildren().add(camera);
        FxGameSpace s = new FxGameSpace(5, 10, 10, 0.15);
             
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(Color.WHITE);
        world.getChildren().addAll(ambient);
        world.getChildren().addAll(s);
        world.getChildren().addAll(cube);
    
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.debug("Start Application");

        buildScene();
        Scene scene = new Scene(root, 768, 768,true,SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);

        primaryStage.setTitle(trans.getTranslate(Locale.getDefault(), KEY.APP_NAME));
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        handleKeyboard(scene, world);
        
    }

    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        launch(args);
    }
    private void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case X:
                        LOGGER.debug("Pressed: X");
                        
                        cube.setColor(new Color( Math.random(), Math.random(), Math.random(),1));
                        break;
                default:
                    break;
                    
                }
            }
        });
    }
}
