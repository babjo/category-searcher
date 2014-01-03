package lch.category_searcher.ui;

import java.awt.FlowLayout;
import java.awt.Label;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import lch.category_searcher.CategorySearcher;

public class ProgressFrame extends JFrame{
	
	private JProgressBar progressbar = new JProgressBar();
	private JLabel current = new JLabel("0");
	private JLabel max = new JLabel("0");

	public ProgressFrame(String name, CategorySearcher searcher, File selectedFile){
		setTitle("진행중...");
		setLayout(new FlowLayout());
		add(progressbar);
		add(current);
		add(new Label(" / "));
		add(max);
		setName(name);
		pack();
		new CategorySearcherWorker(searcher, selectedFile, this).execute();
	}

	public void setMinAndMax(int min, int max){
		this.progressbar.setMinimum(min);
		this.progressbar.setMaximum(max);
		this.current.setText("0");
		this.max.setText(String.valueOf(max));
	}
	
	public void setCurrent(int current){
		this.progressbar.setValue(current);
		this.current.setText(String.valueOf(current));
	}
}
