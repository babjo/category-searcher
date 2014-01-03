package lch.category_searcher;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lch.category_searcher.ui.ProgressFrame;

public class CategorySearcherUI extends JFrame{

	public static final String MAIN_WINDOW = "searcher";
	public static final String LOAD_BUTTON = "load";
	public static final String OK_BUTTON = "ok";
	public static final String EXCEL_CHOOSER = "chooser";
	public static final String LOAD_FAIL_DIALOG = "load_fail_dialog";
	public static final String PROGRESS_DIALOG = "progress";
	public static final String LOAD_FAIL_DIALOG_TITLE = "load fail";
	
	//public static final String CHOOSER_DEFAULT_PATH = "C:\\Users\\LCH\\Documents\\workspace-sts-3.2.0.RELEASE\\category-searcher\\test_excel";
	public static final String CHOOSER_DEFAULT_PATH = CategorySearcherUI.class.getResource(".").getPath();
	
	private CategorySearcher searcher = new CategorySearcher();
	
	public CategorySearcherUI(){
		JButton loadButton = button("엑셀 불러오기", LOAD_BUTTON);
		loadButton.addActionListener(actionListener());
		add(loadButton);
	}

	private ActionListener actionListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFileChooser();
			}
			private void showFileChooser() {
				JFileChooser fileChooser = fileChooser(EXCEL_CHOOSER);
				int ret = fileChooser.showDialog(getFocusOwner(), "open");
				if(ret == 0){
					File selectedFile = fileChooser.getSelectedFile();
					if(searcher.isVaild(selectedFile))
						showProgressDialog(selectedFile);
					else
						showLoadFailDialog();
				}
			}
			private void showProgressDialog(File selectedFile) {
				JFrame frame = new ProgressFrame(PROGRESS_DIALOG, searcher, selectedFile);
				setLocation(frame);
				frame.setVisible(true);
			}
			private void showLoadFailDialog() {
				JFrame frame = dialog(LOAD_FAIL_DIALOG, LOAD_FAIL_DIALOG_TITLE, "check excel form");
				setLocation(frame);
				frame.setVisible(true);
			}
		};
	}
	private JFrame dialog(String name, String title, String message){
		final JFrame frame = new JFrame();
		frame.setTitle(title);
		frame.setLayout(new FlowLayout());
		frame.add(new JLabel(message));
		JButton button = new JButton("ok");
		button.setName(OK_BUTTON);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.add(button);
		frame.setName(name);
		frame.pack();
		return frame;
	}
	
	private JFileChooser fileChooser(String name) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(CHOOSER_DEFAULT_PATH));
		fileChooser.setName(name);
		return fileChooser;
	}
	
	private JButton button(String text, String name) {
        JButton button = new JButton(text);
        button.setName(name);
        button.setMargin(null);
        return button;
    }
	
	public static void main(String... args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CategorySearcherUI.createAndShowGUI();
            }
        });
    }
	
	public static void createAndShowGUI() {
		CategorySearcherUI s = new CategorySearcherUI();
		setLocation(s);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setName(MAIN_WINDOW);
		s.setTitle("category-searcher");
		s.pack();
		s.setVisible(true);
	}

	private static void setLocation(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width/2 - frame.getWidth()/2, screenSize.height/2 - frame.getHeight()/2);
	}
}
