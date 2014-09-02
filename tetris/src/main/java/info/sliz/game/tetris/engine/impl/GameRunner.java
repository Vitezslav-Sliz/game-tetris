package info.sliz.game.tetris.engine.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.concurrent.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;
import info.sliz.game.tetris.engine.command.ICommand.MoveCommandException;

final class GameRunner extends Thread{
    private static ExecutorService QUEUE = Executors.newFixedThreadPool(10);
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    
    private final int time;
    private boolean run;
    private final ICommand move;
    private final ICommand change;
    public GameRunner(final int time, final ICommand move, final ICommand change) {
        this.time = time;
        this.run = true;
        this.move = move;
        this.change = change;
    }
    public void Stop(){
        this.run = false;
    }
    @Override
    public void run() {
        LOGGER.debug(String.format("Running game... with '%s' ms time interval",this.time));
        while (run) {
            QUEUE.execute(new RunnerTask(move,change));
            try {
                Thread.sleep(this.time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }               
        }
        QUEUE.shutdown();
        LOGGER.debug("Stopping game... ");
    }
    final class RunnerTask extends Task<Void> {

        private final ICommand move;
        private final ICommand change;

        public RunnerTask(final ICommand move, final ICommand change) {
            this.move = move;
            this.change = change;
        }

        @Override
        protected Void call() throws Exception {
            LOGGER.debug("Running Move Task");
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        move.execute();
                    } catch (MoveCommandException e1) {
                        try {
                            change.execute();
                        } catch (CommandException e) {
                            LOGGER.debug("Problem with change element",e);
                        }
                    } catch (CommandException e) {
                        LOGGER.debug("Problem with move",e);
                    }
                }
            });
            return null;
        }
    }
} 