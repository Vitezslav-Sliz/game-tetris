package info.sliz.game.tetris.engine.command.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import info.sliz.game.tetris.engine.ICollidable;
import info.sliz.game.tetris.engine.command.ICommand;
import info.sliz.game.tetris.engine.elements.IElements;
import info.sliz.game.tetris.engine.elements.playcube.IMovable;

public class CommandUpdateGameElements implements ICommand, Comparator<IMovable>, Predicate<IMovable>{

    private final IElements elements;
    private final int levelcount;
    private final int step;
    protected final ICollidable colidate;
    private Double d;
    
    public CommandUpdateGameElements(IElements elements, final ICollidable colidate, final int levelcount, final int moveStep) {
        this.elements = elements;
        this.levelcount = levelcount;
        this.step = moveStep;
        this.colidate = colidate;
        d = Double.NaN;
    }
    
    public void execute() throws CommandException {
        while (true) {
            this.d = CommandUpdateGameElements.getZwithFullLevel(this.elements.getMovable(),levelcount);
            if (this.d.isNaN()){
                break;
            }
            List<Object> toRemove = new ArrayList<Object>();
            List<Object> part = this.elements.getMovable().stream().filter(this).collect(Collectors.toList());
            toRemove.addAll(part);
            this.elements.removeAll(toRemove);
            List<IMovable> sorted = new ArrayList<IMovable>(this.elements.getMovable());
            Collections.sort(sorted, this);
            Collections.reverse(sorted);
            Set<ICollidable> col = new HashSet<ICollidable>();
            col.addAll(this.elements.getColidable());
            col.add(this.colidate);
            for (IMovable iMovable : sorted) {
                new CommandMoveForward(iMovable,step,col).execute();;
            }
        }
    }

    private final static Double getZwithFullLevel(List<IMovable> elements, final int levelcount){
        Map<Double, Integer> elByZ = new HashMap<Double, Integer>();
        for (IMovable e : elements) {
            Integer v = elByZ.get(e.getElementCoordinate().getZ()); 
            if (v == null){
                elByZ.put(e.getElementCoordinate().getZ(), new Integer(1));
            }else{
                elByZ.put(e.getElementCoordinate().getZ(), new Integer(++v));
            }
        }
        for (final Entry<Double,Integer> entry : elByZ.entrySet()) {
            if (entry.getValue() == levelcount){
               return entry.getKey();
            }
        }
        return Double.NaN;
        
    }
    public int compare(IMovable o1, IMovable o2) {
        return Double.compare(o1.getElementCoordinate().getZ(), o2.getElementCoordinate().getZ());
    }

    public boolean test(IMovable t) {
        return  t.getElementCoordinate().getZ() == this.d;
    }

}
