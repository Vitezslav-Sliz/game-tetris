package info.sliz.game.tetris.app;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.config.IConfiguration;
import info.sliz.game.tetris.engine.impl.CoreEngine;
import info.sliz.game.tetris.translate.ITranslator;
import info.sliz.game.tetris.translate.Translate.KEY;
import info.sliz.game.tetris.translate.impl.Translator;
import info.sliz.game.tetris.ui.graphic.control.KeyboardControl;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

public class TetrisGameRunner extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(TetrisGameRunner.class);
    private final ITranslator trans = Translator.getInstance();
    private final Group root = new Group();
    private final Group world = new Group();
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private CoreEngine engine = new CoreEngine();
    private KeyboardControl ctrl = new KeyboardControl(engine);
    private void buildScene() {
        root.getChildren().add(world);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-200);
        root.getChildren().add(camera);
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(IConfiguration.COLOR_LIGHT);
        world.getChildren().addAll(ambient);
        world.getChildren().addAll(engine.getElements());
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.debug("Start Application");

        buildScene();
        Scene scene = new Scene(root, 768, 768,true,SceneAntialiasing.BALANCED);
        scene.setFill(IConfiguration.COLOR_UNIVERSE);

        primaryStage.setTitle(trans.getTranslate(Locale.getDefault(), KEY.APP_NAME));
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        scene.setOnKeyPressed(ctrl);
    }

    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        launch(args);
    }
}
