import javax.swing.*;

public class Exeraolic extends JFrame {

    private Exer exr;
    private equipamneto qe;


    public Exeraolic() {

        super();
        try {
            setTitle("cadastro");
            setSize(400,300);
            exr=new Exer();
            this.setContentPane(exr.getPainel());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            exr.getPainel();


        }catch (Exception e){

        }

     setVisible(true);






    }
}
