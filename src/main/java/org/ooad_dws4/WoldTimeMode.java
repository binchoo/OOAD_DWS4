package org.ooad_dws4;

public class WoldTimeMode extends Mode{
    private City[] cities;
    private int currentCityIndex;
    private int thisTimeZone;

    public WoldTimeMode() {
        this.cities = new City[4];
        for(int i = 0; i < 4; i++){
            this.cities[i] = new City(false, 3);
        }
        this.currentCityIndex = 0;
    }

    public void changeCityIndex() {
        // TODO implement here
    }

    private void clacOffsetDif() {
        // TODO implement here
    }

    public void changeCity() {
        // TODO implement here
    }

    public void setCity() {
        // TODO implement here
    }

    private void toggleActivation(){}
    public Message getModeData(){
        return null;
    }

    @Override
    public void toggleModeActivation() {

    }

    @Override
    public Message modeModify(int event) {
        return null;
    }

    @Override
    public void changeState(int state) {

    }
}