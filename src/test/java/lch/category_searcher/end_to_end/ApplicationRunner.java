package lch.category_searcher.end_to_end;

import static lch.category_searcher.CategorySearcherUI.EXCEL_CHOOSER;
import static lch.category_searcher.CategorySearcherUI.LOAD_BUTTON;
import static lch.category_searcher.CategorySearcherUI.LOAD_FAIL_DIALOG;
import static lch.category_searcher.CategorySearcherUI.LOAD_FAIL_DIALOG_TITLE;
import static lch.category_searcher.CategorySearcherUI.MAIN_WINDOW;
import static lch.category_searcher.CategorySearcherUI.OK_BUTTON;
import static lch.category_searcher.CategorySearcherUI.PROGRESS_DIALOG;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import lch.category_searcher.CategorySearcherUI;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.ComponentDriver;
import com.objogate.wl.swing.driver.JButtonDriver;
import com.objogate.wl.swing.driver.JFileChooserDriver;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JProgressBarDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;

public class ApplicationRunner {
	
	JFrameDriver mainWindow;
	JFrameDriver loadFailDialog;
	public static final String INCORRECT_EXCEL = "incorrect.xls";
	public static final String CORRECT_EXCEL = "correct.xls";
	
	public void run() {
		///Main.main(null);
		CategorySearcherUI.main();
		mainWindow = driver(MAIN_WINDOW);
	}

	public void loadIncorrectFile() {
		select(INCORRECT_EXCEL);
	}
	
	public void loadCorrectFile() {
		select(CORRECT_EXCEL);
	}
	
	private void select(String path) {
		button(mainWindow, LOAD_BUTTON).click();
		fileChooser(EXCEL_CHOOSER).selectFile(path);
		fileChooser(EXCEL_CHOOSER).approve();
	}

	public void showLoadFailDialog() {
		loadFailDialog = driver(LOAD_FAIL_DIALOG);
		loadFailDialog.hasTitle(LOAD_FAIL_DIALOG_TITLE);
	}
	
	public void showProgressBar() {
		driver(PROGRESS_DIALOG);
	}

	public void clickFailDialogOk() {
		button(loadFailDialog, OK_BUTTON).click();
	}
	
	private JProgressBarDriver progressBar(ComponentDriver<? extends Component> parentOrOwner, String name){
		return new JProgressBarDriver(parentOrOwner, JProgressBar.class, ComponentDriver.named(name));
	}
	
	private JFrameDriver driver(String name) {
		return new JFrameDriver(new GesturePerformer(), new AWTEventQueueProber(), ComponentDriver.named(name), ComponentDriver.showingOnScreen());
	}
	
	@SuppressWarnings("unchecked")
	private JButtonDriver button(ComponentDriver<? extends Component> parentOrOwner, String name) {
		return new JButtonDriver(parentOrOwner, JButton.class, ComponentDriver.named(name));
	}
	
	private JFileChooserDriver fileChooser(String name) {
		return new JFileChooserDriver(mainWindow, ComponentDriver.named(name));
	}

	public void stop() {
		mainWindow.dispose();
	}
}
