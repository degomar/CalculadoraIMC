package co.tiagoaguiar.codelab.myapplication;

public class MainItem {
    private int idRv;
    private int drawable;
    private int textStringId;
    private int color;

    public MainItem(int idRv, int drawable, int textStringId, int color) {
        this.idRv = idRv;
        this.drawable = drawable;
        this.textStringId = textStringId;
        this.color = color;
    }

    public int getIdRv() {
        return idRv;
    }

    public void setIdRv(int idRv) {
        this.idRv = idRv;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public int getTextStringId() {
        return textStringId;
    }

    public void setTextStringId(int textStringId) {
        this.textStringId = textStringId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
