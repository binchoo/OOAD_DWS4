package org.ooad_dws4.systemtest;

import java.util.ArrayList;

public class CombinationGenerator {

    private int[] cache;
    private ArrayList<int[]> combination;
    private int n;
    private int r;

    public CombinationGenerator(int n, int r) {
        this.n = n;
        this.r = r;
        cache = new int[r];
        combination = new ArrayList<>();

        make();
    }

    private void make() {
        traversal(0, n - r, 0);
    }

    private void traversal(int start, int last, int phase) {
        int remaining = r - phase;
        if (remaining == 0) {
            combination.add(cache.clone());
        } else {
            for(int i = start; i <= last; i++) {
                cache[phase] = i;
                traversal(i + 1, n - remaining + 1, phase + 1);
            }
        }
    }

    public ArrayList<int[]> getCombination() {
        return combination;
    }
}

class ModeCombinationGenerator extends CombinationGenerator {

    private ArrayList<SystemMocker.Mode> modeSetCache;
    private ArrayList<ArrayList<SystemMocker.Mode> > modeCombination;

    public ModeCombinationGenerator(int n, int r) {
        super(n - 1, r - 1);
        modeSetCache = new ArrayList<>();
        modeCombination = new ArrayList<>();

        wrap();
    }

    public void wrap() {
        ArrayList<int[]> comb = super.getCombination();
        for(int[] set : comb) {
            modeSetCache.add(SystemMocker.Mode.TIME_KEEPING);
            for(int index : set) {
                modeSetCache.add(SystemMocker.Mode.get(index + 1));
            }
            modeCombination.add((ArrayList<SystemMocker.Mode>) modeSetCache.clone());
            modeSetCache.clear();
        }
    }

    public ArrayList<ArrayList<SystemMocker.Mode> > getModeCombination() {
        return modeCombination;
    }
}