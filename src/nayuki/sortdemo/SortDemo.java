package nayuki.sortdemo;

/*
Color legend:
  Blue: Normal
  Green: In final position
  Yellow: Comparing
  Gray: Inactive
*/


import java.awt.Button;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import nayuki.sortdemo.algor.*;


@SuppressWarnings("serial")
public class SortDemo extends Frame implements ActionListener {
	
	public static void main(String[] args) {
		// Set up list of algorithms
		List<SortAlgorithm> algors = new ArrayList<SortAlgorithm>();
		algors.add(new BubbleSort());
		algors.add(new SelectionSort());
		algors.add(new InsertionSort());
		algors.add(new InsertionSort1());
		algors.add(new ShellSort());
		algors.add(new HeapSort());
		algors.add(new QuickSort0());
		algors.add(new QuickSort1());
		algors.add(new StoogeSort());
		algors.add(new StupidSort());
		algors.add(new BozoSort());
		
		// Go!
		new SortDemo(algors);
	}
	
	
	
	private List<SortAlgorithm> algorithms;
	
	private TextField numberInput;
	private TextField scaleInput;
	private TextField delayInput;
	private Choice algorithmInput;
	private Button runButton;
	
	
	
	public SortDemo(List<SortAlgorithm> algors) {
		super("Sort Demo");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.weighty = 0;
		Label label;
		
		gbc.gridx = 0;
		label = new Label("Method:");
		gbc.weightx = 1;
		gbc.gridy = 0;
		gbl.setConstraints(label, gbc);
		add(label);
		
		label = new Label("Number:");
		gbc.gridy = 1;
		gbl.setConstraints(label, gbc);
		add(label);
		
		label = new Label("Scale:");
		gbc.gridy = 2;
		gbl.setConstraints(label, gbc);
		add(label);
		
		label = new Label("Delay (ms):");
		gbc.gridy = 3;
		gbl.setConstraints(label, gbc);
		add(label);
		
		gbc.gridx = 1;
		gbc.weightx = 2;
		algorithms = new ArrayList<SortAlgorithm>(algors);
		algorithmInput = new Choice();
		for (SortAlgorithm algor : algors)
			algorithmInput.add(algor.getName());
		gbc.gridy = 0;
		gbl.setConstraints(algorithmInput, gbc);
		add(algorithmInput);
		
		numberInput = new TextField("32");
		numberInput.addActionListener(this);
		gbc.gridy = 1;
		gbl.setConstraints(numberInput, gbc);
		add(numberInput);
		
		scaleInput = new TextField("8");
		scaleInput.addActionListener(this);
		gbc.gridy = 2;
		gbl.setConstraints(scaleInput, gbc);
		add(scaleInput);
		
		delayInput = new TextField("100");
		delayInput.addActionListener(this);
		gbc.gridy = 3;
		gbl.setConstraints(delayInput, gbc);
		add(delayInput);
		
		runButton = new Button("Run");
		runButton.addActionListener(this);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbl.setConstraints(runButton, gbc);
		add(runButton);
		
		pack();
		Rectangle rect = getGraphicsConfiguration().getBounds();
		setLocation((rect.width - getWidth()) / 2, (rect.height - getHeight()) / 3);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == runButton) {
			try {
				int size = Integer.parseInt(numberInput.getText());
				int scale = Integer.parseInt(scaleInput.getText());
				int delay = Integer.parseInt(delayInput.getText());
				
				SortArray array = new SortArray(size, scale, delay);
				Canvas canvas = array.getCanvas();
				SortAlgorithm algorithm = algorithms.get(algorithmInput.getSelectedIndex());
				SortThread thread = new SortThread(algorithm, array);
				new SortFrame(canvas, algorithm, thread);
				thread.start();
			} catch (NumberFormatException ee) {}
		}
	}
	
}
