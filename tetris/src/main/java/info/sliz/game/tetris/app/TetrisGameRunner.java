package info.sliz.game.tetris.app;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.translate.ITranslator;
import info.sliz.game.tetris.translate.Translate.KEY;
import info.sliz.game.tetris.translate.impl.Translator;
import info.sliz.game.tetris.ui.gamespace.FxGameSpace;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TetrisGameRunner extends Application {
    private final Logger logger = LoggerFactory.getLogger(TetrisGameRunner.class);
    private final ITranslator trans = Translator.getInstance();
    private final Group root = new Group();
    private final Group world = new Group();
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    
    private void buildScene() {
        System.out.println("buildScene");
        root.getChildren().add(world);
        
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-320);
        root.getChildren().add(camera);
        world.getChildren().addAll(new FxGameSpace(9, 15, 10, 0.5));
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Start Application");

        buildScene();
        Scene scene = new Scene(root, 768, 768);
        scene.setFill(Color.BLACK);

        primaryStage.setTitle(trans.getTranslate(Locale.getDefault(), KEY.APP_NAME));
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        
    }

    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        launch(args);
    }
}
