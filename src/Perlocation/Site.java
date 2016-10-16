package Perlocation;

public class Site {

    private boolean isOpen;
    private boolean isBlocked;
    private boolean isFull;

    public Site (){
        this.isOpen = false;
        this.isBlocked = true;
        this.isFull = false;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isBlocked() {
        return isBlocked;
    }



    public void open() {
        isOpen = true;
        isBlocked = false;
    }

    public void fill() {
        isFull = true;
    }

}
