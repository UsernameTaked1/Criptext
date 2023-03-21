import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Criptext extends JFrame {
    private File f, fc;
    private final User u;
    private final JPanel panel, btns;
    private JTextArea c, dc;
    private JButton cifrar, guardarc, guardardsc, descifrar, leer;
    String ruta;

    public Criptext() {
        ruta = System.getProperty("user.dir");
        f = new File(ruta + "\\mensajedsc.txt");
        fc = new File(ruta + "\\mensajec.vge");
        u = new User();
        panel = new JPanel();
        btns = new JPanel();
        cifrar = new JButton("Cifrar");
        descifrar = new JButton("Descifrar");
        guardarc = new JButton("Guardar texto cifrado");
        guardardsc = new JButton("Guardar texto descifrado");
        leer = new JButton("Leer archivo cifrado");
        c = new JTextArea("Texto cifrado");
        dc = new JTextArea("Texto descifrado");
        setSize(750, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        show(true);
        atributos();
        armado();
        escuchas();
    }

    public void armado() {
        add(panel, BorderLayout.CENTER);
        add(btns, BorderLayout.EAST);
        panel.add(dc, BorderLayout.EAST);
        panel.add(cifrar, BorderLayout.CENTER);
        panel.add(descifrar, BorderLayout.CENTER);
        panel.add(c, BorderLayout.WEST);
        btns.add(guardardsc, BorderLayout.CENTER);
        btns.add(guardarc, BorderLayout.CENTER);
        btns.add(leer, BorderLayout.CENTER);
    }

    public void atributos() {
        setResizable(false);
        c.setSize(150, 150);
        dc.setSize(150, 150);
        c.setRows(15);
        dc.setRows(15);
        c.setLineWrap(true);
        dc.setLineWrap(true);
        cifrar.setSize(70, 30);
        panel.setBackground(new Color(229, 217, 189));
        btns.setBackground(new Color(229, 217, 189));
        btns.setLayout(new BoxLayout(btns, BoxLayout.PAGE_AXIS));
    }

    public void escuchas() {
        cifrar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.setText(u.cifrado(dc.getText()));
                    }
                });
        descifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dc.setText(u.descifrado(c.getText()));
            }
        });
        guardarc.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        try {
                            if (!fc.exists()) {
                                fc.createNewFile();
                            }
                            FileWriter fw = new FileWriter(f);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(dc.getText());
                            bw.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

        );
        guardardsc.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (!f.exists()) {
                                f.createNewFile();
                            }
                            FileWriter fw = new FileWriter(f);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(c.getText());
                            bw.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }

        );
        leer.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            FileInputStream fis = new FileInputStream(fc);
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            Object obj;
                            String s = "";
                            while ((obj = ois.readObject()) != null) {
                                if (obj instanceof String) {
                                    s += (String) obj;
                                }
                            }
                            ois.close();
                            c.setText(s);
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
    }

    public static void main(String[] args) {
        Criptext u = new Criptext();
        System.out.println(System.getProperty("user.dir"));
    }
}
