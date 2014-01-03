package lch.category_searcher.ui;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import lch.category_searcher.CategorySearcher;

public class CategorySearcherWorker extends SwingWorker<Void, Void> {

	private File selectedFile;
	private CategorySearcher searcher;
	private ProgressFrame progressFrame;

	public CategorySearcherWorker(CategorySearcher searcher,
			File selectedFile, ProgressFrame progressFrame) {
		this.searcher = searcher;
		this.selectedFile = selectedFile;
		this.progressFrame = progressFrame;
	}

	@Override
	public Void doInBackground() throws Exception {
		searcher.search(selectedFile, this);
		return null;
	}
	
	@Override
    public void done() {
		JOptionPane.showMessageDialog(this.progressFrame,
			    "완료");
		this.progressFrame.dispose();
    }
	
	public void setMinAndMax(int min, int max){
		this.progressFrame.setMinAndMax(min, max);
	}
	
	public void setCurrent(int current){
		this.progressFrame.setCurrent(current);
	}
}
