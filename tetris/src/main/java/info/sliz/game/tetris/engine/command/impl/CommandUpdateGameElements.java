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

public class CommandUpdateGameElements implements ICommand, Comparator<IMovable>{

    private final IElements elements;
    private final int levelcount;
    private final int step;
    protected final ICollidable colidate;
    
    public CommandUpdateGameElements(IElements elements, final ICollidable colidate, final int levelcount, final int moveStep) {
        this.elements = elements;
        this.levelcount = levelcount;
        this.step = moveStep;
        this.colidate = colidate;
    }
    
    public void execute() throws CommandException {
        Map<Double, Integer> elByZ = new HashMap<Double, Integer>();
        for (IMovable e : this.elements.getMovable()) {
            Integer v = elByZ.get(e.getControlPoint().getZ()); 
            if (v == null){
                elByZ.put(e.getControlPoint().getZ(), new Integer(1));
            }else{
                elByZ.put(e.getControlPoint().getZ(), new Integer(++v));
            }
        }
        List<Object> toRemove = new ArrayList<Object>();
        for (final Entry<Double,Integer> entry : elByZ.entrySet()) {
            if (entry.getValue() == levelcount){
                Predicate<IMovable> p = new Predicate<IMovable>(){
                    public boolean test(IMovable t) {
                        return  t.getControlPoint().getZ() == entry.getKey();
                    }
                };
                List<Object> part = this.elements.getMovable().stream().filter(p).collect(Collectors.toList());
                toRemove.addAll(part);
            }
        }
        this.elements.removeAll(toRemove);
        List<IMovable> sorted = new ArrayList<IMovable>(this.elements.getMovable());
        Collections.sort(sorted, this);
        Collections.reverse(sorted);
        Set<ICollidable> col = new HashSet<ICollidable>();
        col.addAll(this.elements.getColidable());
        col.add(this.colidate);
        
        for (IMovable iMovable : sorted) {
            new CommandMoveForwardMax(iMovable,step,col).execute();;
        }
    }

    public int compare(IMovable o1, IMovable o2) {
        return Double.compare(o1.getControlPoint().getZ(), o2.getControlPoint().getZ());
    }

}
