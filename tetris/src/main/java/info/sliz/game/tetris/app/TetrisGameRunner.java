package info.sliz.game.tetris.app;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.config.Configuration;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;
import info.sliz.game.tetris.engine.command.impl.CommandStartGame;
import info.sliz.game.tetris.engine.impl.CoreEngine;
import info.sliz.game.tetris.translate.ITranslator;
import info.sliz.game.tetris.translate.Translate.KEY;
import info.sliz.game.tetris.translate.impl.Translator;
import info.sliz.game.tetris.ui.command.impl.ExitCommand;
import info.sliz.game.tetris.ui.graphic.control.KeyboardControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TetrisGameRunner extends Application implements Observer{
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
        ambient.setColor(Configuration.COLOR_LIGHT);
        root.getChildren().addAll(ambient);
        world.getChildren().addAll(engine.getElements());
        engine.addObserver(this);
        
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.debug("Start Application");

        buildScene();
        Scene scene = new Scene(root, 768, 768,true,SceneAntialiasing.BALANCED);
        scene.setFill(Configuration.COLOR_UNIVERSE);

        primaryStage.setTitle(trans.getTranslate(Locale.getDefault(), KEY.APP_NAME));
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        scene.setOnKeyPressed(ctrl);
        scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent ev) {
                new ExitCommand(engine).execute();
            }
        });
        try {
			this.engine.getCommand(CommandStartGame.class).execute();
		} catch (CommandException e) {
			LOGGER.error("Can't start game",e);
		}
    }

    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        launch(args);
        
    }

    public void update(Observable o, Object arg) {
        world.getChildren().removeAll(engine.getElements());
        world.getChildren().addAll(engine.getElements());
        world.getChildren().retainAll(engine.getElements());
        for (Node node : engine.getElements()) {
            if (!world.getChildren().contains(node)){
                world.getChildren().add(node);
            }
        }
    }
}
