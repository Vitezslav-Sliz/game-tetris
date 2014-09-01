package info.sliz.game.tetris.engine.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.concurrent.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.command.ICommand.CommandException;

final class GameRunner extends Thread{
    private static ExecutorService QUEUE = Executors.newFixedThreadPool(10);
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    
    private final int time;
    private boolean run;
    private final ICommand c;
    public GameRunner(final int time, ICommand c) {
        this.time = time;
        this.run = true;
        this.c = c;
    }
    public void Stop(){
        this.run = false;
    }
    @Override
    public void run() {
        LOGGER.debug(String.format("Running game... with '%s' ms time interval",this.time));
        while (run) {
            QUEUE.execute(new RunnerTask(c));
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

        private final ICommand c;

        public RunnerTask(ICommand c) {
            this.c = c;
        }

        @Override
        protected Void call() throws Exception {
            LOGGER.debug("Running Move Task");
            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        c.execute();
                    } catch (CommandException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            return null;
        }
    }
} 