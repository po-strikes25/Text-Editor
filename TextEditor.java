import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {

    // Heavy weighted component
    static JFrame frame;
    static JTextArea textArea;
//    static JButton button;
    static JMenuBar menuBar;
    static JMenu File, Edit, Close;
    static JMenuItem New, Open, Save, Print, Cut, Copy, Paste, CloseEditor;
    static JScrollPane scrollBar;

    TextEditor() {

        // Creating UI of the TextEditor
        // To create an object and utilize the functionalities of swing package
        frame = new JFrame("Text Editor");
        // To set boundary of the frame
        frame.setBounds(0, 0, 800, 1000);
        // To set the frame layout - Had to remove this code to enable scrollBar
//        frame.setLayout(null);
        // To make the window visible to the user
        frame.setVisible(true);
        // To close the textEditor Window
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // To create multi-line/area for the user to write
        textArea = new JTextArea("Please write your text here ... ");
        // To set boundary of the textArea
        textArea.setBounds(0, 0, 1500, 1000);
        // To set line wrap
        textArea.setLineWrap(true);

        //To add scrollbar in the textArea
        scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        /*  To add the light weighted component JTextArea into the
            Heavy weighted component JFrame */
        frame.add(scrollBar);

        // To create a button
//        button = new JButton("Clickable Button");
        // To set boundary of the button
//        button.setBounds(600, 100, 200, 80);
        // To add the button in the textArea
//        frame.add(button);
        // To make the button visible to the user
//        button.setVisible(true);
        // To make the button interactive/perform an action
//        button.addActionListener(this);

        // To create MenuBar, Menu and MenuItems
        menuBar = new JMenuBar();
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");
        New = new JMenuItem("New");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        Print = new JMenuItem("Print");
        Cut = new JMenuItem("Cut");
        Copy = new JMenuItem("Copy");
        Paste = new JMenuItem("Paste");
        CloseEditor = new JMenuItem(("Terminate"));

        // To add the menuItems in the menu and menu in the menuBar
        File.add(New);
        File.add(Open);
        File.add(Save);
        File.add(Print);
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);
        Close.add(CloseEditor);
        menuBar.add(File);
        menuBar.add(Edit);
        menuBar.add(Close);

        // To make the menuItems interactive/perform action
        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        Print.addActionListener(this);
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        CloseEditor.addActionListener(this);

        // To make the menuBar visible to the user
        frame.setJMenuBar(menuBar);

    }

    public static void main(String[] args) {
        TextEditor ed = new TextEditor();
    }

    // ActionListener Function - Click Action Based
    @Override
    public void actionPerformed(ActionEvent e) {
        String click = e.getActionCommand();
        if(click.equals("New"))
            textArea.setText("");
        else if(click.equals("Open")) {

            // To provide a path to the JFileChooser to select and open existing files
            JFileChooser fileChooser = new JFileChooser("C:");

            // To store the selection made by the user from the Open Dialog Box
            int ans = fileChooser.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION) {

                // To get and store the absolute Path of the chosen file
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                String str_1 = "", str_2 = "";

                // To read the contents from the chosen file
                try {
                    // To handle the case when absolute Path of the file is NULL
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    // To read the first line
                    str_2 = bufferedReader.readLine();

                    // To store consecutive lines
                    while((str_1 = bufferedReader.readLine()) != null)
                        str_2 += str_1 + "\n";
                    textArea.setText(str_2);

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
        else if(click.equals("Save")) {
            // To provide a path to the JFileChooser to locate existing files
            JFileChooser fileChooser = new JFileChooser("C:");

            // To store the selection made by the user from the Save Dialog Box
            int ans = fileChooser.showSaveDialog(null);
            if (ans == JFileChooser.APPROVE_OPTION) {

                // To get and store the absolute Path of the chosen file
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                    // To get the contents of the file and store it
                    bufferedWriter.write(textArea.getText());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(click.equals("Cut"))
            textArea.cut();
        else if(click.equals("Copy"))
            textArea.copy();
        else if(click.equals("Paste"))
            textArea.paste();
        else if(click.equals("Print")) {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        } else if(click.equals("Terminate")) {
                System.exit(1);
//            frame.setVisible(false);
        }
    }
}
