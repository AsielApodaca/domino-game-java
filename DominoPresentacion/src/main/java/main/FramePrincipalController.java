package main;

public class FramePrincipalController {
    private FramePrincipalModel model;

    public FramePrincipalController(FramePrincipalModel model) {
        this.model = model;
    }

    public void mostrarPanel(int index) {
        FramePrincipalView view = FramePrincipalView.getInstance(model, this);
        view.getCardLayout().show(view.getCardPanel(), "Panel " + (index + 1));
    }
}
