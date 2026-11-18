//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import  javax.swing.SwingUtilities;
void main() {
SwingUtilities.invokeLater(new Runnable() {
    @Override
    public void run() {
        new ProductApp();
    }
});
}
